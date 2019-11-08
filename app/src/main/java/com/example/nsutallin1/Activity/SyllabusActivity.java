package com.example.nsutallin1.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.Adapter.SyllabusAdapter;
import com.example.nsutallin1.Class.Syllabus;
import com.example.nsutallin1.R;

import java.util.ArrayList;

public class SyllabusActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SyllabusAdapter mAdapter;
    private ArrayList<Syllabus> syllabi;

    private static final int SYLLABUS_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);


        Toolbar toolbar = findViewById(R.id.toolbar_syllabus);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SYLLABUS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        syllabi = new ArrayList<>();

        syllabi.add(new Syllabus("Electronics and Communication Engineering", R.drawable.ece, "143Eca437QUQvW83u5Gy-pjaBtP2M4Dha"));
        syllabi.add(new Syllabus("Computer Engineering", R.drawable.coe, "14BUJuR4PexMjBccRL_t4DQglirA68pNq"));
        syllabi.add(new Syllabus("Information Technology", R.drawable.it, "14Eb7TZEH7EY7aAOCby5mULYjDrAm7dM2"));
        syllabi.add(new Syllabus("Instrumentation and Control Engineering", R.drawable.ice, "14GD1OJTE9_k8CJ5PeekhfFXjDunELLl-"));
        syllabi.add(new Syllabus("Manufacturing Processes and Automation Engineering", R.drawable.mpae, "14Nj87Pm4iiWD4dj8BaaaZxsgwJUiWLi4"));
        syllabi.add(new Syllabus("Mechanical Engineering", R.drawable.me, "14RDozAq8qesAbrs5RLjScTzlrNgmc8DK"));
        syllabi.add(new Syllabus("Biotechnology", R.drawable.bt, "14RW0NXb-hPfmzSHLLWidpMG0NnPB48lZ"));

        mRecyclerView = findViewById(R.id.syllabus_rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new SyllabusAdapter(syllabi, this);
        mRecyclerView.setAdapter(mAdapter);
    }

}
