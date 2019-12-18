package com.ourcoolapp.nsutresources.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ourcoolapp.nsutresources.Adapter.DataAdapter;
import com.ourcoolapp.nsutresources.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataResultActivity extends AppCompatActivity implements DataAdapter.ListItemClickListener {

    private String selBranch, selData, subName;
    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;
    private List<StorageReference> files;
    private ArrayList<String> data;
    private static int REQUEST_PERMISSION = 2;
    private LinearLayout emptyLayout;
    private ImageView emptyIV;
    private TextView emptyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_result);

        Intent intent = getIntent();
        selBranch = intent.getStringExtra("branchName").toLowerCase();
        selData = intent.getStringExtra("dataType");
        subName = intent.getStringExtra("subName");

        emptyLayout = findViewById(R.id.empty_layout);
        emptyIV = findViewById(R.id.empty_image_view);
        emptyTV = findViewById(R.id.empty_text_view);
        mRecyclerView = findViewById(R.id.data_rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        files = new ArrayList<>();
        data = new ArrayList<>();

        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            FirebaseStorage storage = FirebaseStorage.getInstance();
            final StorageReference listRef = storage.getReference().child(selBranch + "/" + subName + "/" + selData);

            listRef.listAll()
                    .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                        @Override
                        public void onSuccess(ListResult listResult) {
                            files = listResult.getItems();
                            for (StorageReference item : listResult.getItems()) {
                                data.add(item.getName().split(".pdf")[0]);
                            }

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
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(DataResultActivity.this, "Sorry, an error occured!", Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            emptyIV.setImageResource(R.drawable.no_internet);
            emptyTV.setText(R.string.no_internet_connection);
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);
        }

    }

    @Override
    public void OnDataItemClick(final int index) {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        }

        final File appDir = getExternalFilesDir(null);
        File listFile[] = appDir.listFiles();
        if (listFile != null && listFile.length > 0) {
            int check = 0;
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].getName().split("&")[0].equals("." + data.get(index))) {
                    check = 1;

                    Intent intent = new Intent(this, PdfActivity.class);
                    intent.putExtra("name", data.get(index));
                    startActivity(intent);
                }
            }
            if (check == 0) {
                DownloadFile(index);
            }
        } else {
            DownloadFile(index);
        }
    }

    private void DownloadFile(final int index) {
        final File file = new File(getExternalFilesDir(null), "." + data.get(index) + "&" + selData + ".encrypted");

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();

        files.get(index).getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                Intent intent = new Intent(DataResultActivity.this, PdfActivity.class);
                intent.putExtra("name", data.get(index));
                startActivity(intent);

                pd.cancel();
            }
        }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull FileDownloadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                pd.setMessage((int) progress + "%");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DataResultActivity.this, "Sorry, an error occured!", Toast.LENGTH_SHORT).show();
                pd.cancel();
            }
        });
    }
}
