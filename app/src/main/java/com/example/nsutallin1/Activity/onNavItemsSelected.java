package com.example.nsutallin1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.Adapter.DataAdapter;
import com.example.nsutallin1.Class.Data;
import com.example.nsutallin1.R;

import java.util.ArrayList;

public class onNavItemsSelected extends AppCompatActivity {

    String selectedBranch;
    String navSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.on_nav_items_selected);

        Intent intent=getIntent();
        if (intent.hasExtra("barnchName") && intent.hasExtra("navSelectedItem"))
        {
            selectedBranch=intent.getStringExtra("branchName");
            navSelection=intent.getStringExtra("navSelectedItem");
        }

        RecyclerView RecyclerView_notes=findViewById(R.id.rv_notes);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView_notes.setLayoutManager(linearLayoutManager);


        RecyclerView_notes.setHasFixedSize(true);

        ArrayList<Data> data = new ArrayList<>();

// TODO Populate the adapter please
        RecyclerView_notes.setHasFixedSize(true);
        //RecyclerView_notes.setAdapter(dataAdapter);
    }
}
