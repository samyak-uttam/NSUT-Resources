package com.example.nsutallin1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nsutallin1.Adapter.DataAdapter;
import com.example.nsutallin1.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_result);

        Intent intent = getIntent();
        selBranch = intent.getStringExtra("branchName").toLowerCase();
        selData = intent.getStringExtra("dataType");
        subName = intent.getStringExtra("subName");

        mRecyclerView = findViewById(R.id.data_rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.HORIZONTAL));
        files = new ArrayList<>();
        data = new ArrayList<>();

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
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DataResultActivity.this, "Sorry, an error occured!", Toast.LENGTH_SHORT).show();
                    }
                });

        mAdapter = new DataAdapter(data, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void OnDataItemClick(final int index) {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        }

        final File appDir = getExternalFilesDir(null);
        File listFile[] = appDir.listFiles();
        if(listFile != null && listFile.length > 0) {
            int check = 0;
            for(int i = 0; i < listFile.length; i++) {
                if(listFile[i].getName().equals(data.get(index) + ".pdf")) {
                    check = 1;
                    Intent intent = new Intent(this, PdfActivity.class);
                    intent.putExtra("index", i);
                    startActivity(intent);
                }
            }
            if(check == 0) {
                DownloadFile(index);
            }
        } else {
            DownloadFile(index);
        }
    }

    private void DownloadFile(final int index) {
        final File localFile = new File(getExternalFilesDir(null), data.get(index) + ".pdf");

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();

        files.get(index).getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                File listFile[] = getExternalFilesDir(null).listFiles();
                for(int i = 0; i < listFile.length; i++) {
                    if(listFile[i].getName().equals(data.get(index) + ".pdf")) {
                        Intent intent = new Intent(DataResultActivity.this, PdfActivity.class);
                        intent.putExtra("index", i);
                        startActivity(intent);
                    }
                }
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
