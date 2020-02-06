package com.educational.nsutresources.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.educational.nsutresources.Adapter.DataAdapter;
import com.educational.nsutresources.Data.BookDbHelper;
import com.educational.nsutresources.Data.ContractClass.BookEntry;
import com.educational.nsutresources.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataResultActivity extends AppCompatActivity implements DataAdapter.ListItemClickListener {

    private String selBranch, selData, subName;
    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;
    private ArrayList<String> data;
    private LinearLayout emptyLayout;
    private ImageView emptyIV;
    private TextView emptyTV;
    private DbxRequestConfig config;
    private DbxClientV2 client;
    private static final String ACCESS_TOKEN = "etGspIjOmAAAAAAAAAAASbfIDBHQQ_vOOfBzBitsACm4baC6ytb_Jj8zcnpJaeAY";
    private String path;
    private BookDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_result);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        selBranch = intent.getStringExtra("branchName").toLowerCase();
        selData = intent.getStringExtra("dataType");
        subName = intent.getStringExtra("subName");

        mDbHelper = new BookDbHelper(this);

        config = new DbxRequestConfig("dropbox/NSUT Resources");
        client = new DbxClientV2(config, ACCESS_TOKEN);
        path = "/NSUT Resources/" + selBranch + "/" + subName + "/" + selData;

        emptyLayout = findViewById(R.id.empty_layout);
        emptyIV = findViewById(R.id.empty_image_view);
        emptyTV = findViewById(R.id.empty_text_view);
        mRecyclerView = findViewById(R.id.data_rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        data = new ArrayList<>();

        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            GetData task = new GetData();
            task.execute();

        } else {
            emptyIV.setImageResource(R.drawable.no_internet);
            emptyTV.setText(R.string.no_internet_connection);
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);
        }

    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                ListFolderResult result = client.files().listFolder(path);

                for (Metadata metadata : result.getEntries()) {
                    data.add(metadata.getName().split(".pdf")[0]);
                }

            } catch (DbxException e) {
                Toast.makeText(DataResultActivity.this, "Sorry, an error occured!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);

            if (!data.isEmpty() && data.size() > 0) {
                mAdapter = new DataAdapter(data, DataResultActivity.this);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            } else {
                emptyIV.setImageResource(R.drawable.empty);
                emptyTV.setText("No material available for this category.");
            }
        }
    }

    @Override
    public void OnDataItemClick(final int index) {

        final File appDir = getExternalFilesDir(null);
        File listFile[] = appDir.listFiles();
        if (listFile != null && listFile.length > 0) {
            int check = 0;
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].getName().equals(data.get(index) + ".pdf")) {
                    check = 1;

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(FileProvider.getUriForFile(this, this.getApplicationContext()
                            .getPackageName() + ".provider", listFile[i]), "application/pdf");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    startActivity(intent);
                }
            }
            if (check == 0) {
                DownloadFile file = new DownloadFile();
                file.execute(index);
            }
        } else {
            DownloadFile file = new DownloadFile();
            file.execute(index);
        }
    }

    private class DownloadFile extends AsyncTask<Integer, Integer, Void> {

        private ProgressDialog pd;
        private int index;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(DataResultActivity.this);
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected Void doInBackground(Integer... integers) {

            index = integers[0];
            final File file = new File(getExternalFilesDir(null),
                    data.get(index) + ".pdf");
            try {
                OutputStream output = new FileOutputStream(file);
                DbxDownloader<FileMetadata> metadata = client.files().download(path.toLowerCase() + "/"
                        + data.get(index).toLowerCase() + ".pdf");
                InputStream input = metadata.getInputStream();
                long length = metadata.getResult().getSize();
                int count;
                long total = 0;
                byte[] data = new byte[1024];
                while ((count = input.read(data)) != -1) {
                    total += count;

                    publishProgress((int) ((total * 100) / length));

                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
            } catch (IOException | DbxException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            pd.setMessage(progress[0] + "%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            addToDatabase(index);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            File[] listFile = getExternalFilesDir(null).listFiles();
            int i = 0;
            for (i = 0; i < listFile.length; i++) {
                if (listFile[i].getName().equals(data.get(index) + ".pdf")) {
                    break;
                }
            }
            intent.setDataAndType(FileProvider.getUriForFile(DataResultActivity.this, getApplicationContext()
                    .getPackageName() + ".provider", listFile[i]), "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);

            pd.cancel();
        }
    }

    private void addToDatabase(int index) {

        SQLiteDatabase writableDatabase = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_BOOK_NAME, data.get(index));
        values.put(BookEntry.COLUMN_BOOK_DATA, selData);

        writableDatabase.insert(BookEntry.TABLE_NAME, null, values);
    }
}
