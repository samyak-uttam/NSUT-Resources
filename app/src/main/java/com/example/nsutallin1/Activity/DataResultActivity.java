package com.example.nsutallin1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nsutallin1.Adapter.DataAdapter;
import com.example.nsutallin1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class DataResultActivity extends AppCompatActivity implements DataAdapter.ListItemClickListener {

    private String selBranch, selData, subName;
    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;
    private List<StorageReference> files;
    private ArrayList<String> data;

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
    public void OnDataItemClick(final int clickedItemIndex) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(files.get(clickedItemIndex).getDownloadUrl().toString()), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Intent newIntent = Intent.createChooser(intent, "Open File");
        try {
            startActivity(newIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(DataResultActivity.this, "Sorry, an error occured!", Toast.LENGTH_SHORT).show();
        }
    }
}
