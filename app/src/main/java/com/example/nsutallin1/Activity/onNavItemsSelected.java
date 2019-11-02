package com.example.nsutallin1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nsutallin1.Adapter.SubjectAdapter;
import com.example.nsutallin1.Class.Data;
import com.example.nsutallin1.Class.Subject;
import com.example.nsutallin1.R;

import java.util.ArrayList;

public class onNavItemsSelected extends AppCompatActivity {

    private String selectedBranch;
    private String navSelection;
    private SubjectAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private static ArrayList<Subject> coeSubs, itSubs, eceSubs, iceSubs, meSubs, mpaeSubs, btSubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.on_nav_items_selected);

        coeSubs = new ArrayList<>();
        itSubs = new ArrayList<>();
        eceSubs = new ArrayList<>();
        iceSubs = new ArrayList<>();
        meSubs = new ArrayList<>();
        mpaeSubs = new ArrayList<>();
        btSubs = new ArrayList<>();

        assignSubjects();

        Intent intent = getIntent();
        if (intent.hasExtra("branchName") && intent.hasExtra("navSelectedItem")) {
            selectedBranch = intent.getStringExtra("branchName");
            navSelection = intent.getStringExtra("navSelectedItem");
        }

        mRecyclerView = findViewById(R.id.rv_notes);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        switch (selectedBranch) {
            case "COE":
                mAdapter = new SubjectAdapter(coeSubs, this);
                break;
            case "IT":
                mAdapter = new SubjectAdapter(itSubs, this);
                break;
            case "ECE":
                mAdapter = new SubjectAdapter(eceSubs, this);
                break;
            case "ICE":
                mAdapter = new SubjectAdapter(iceSubs, this);
                break;
            case "ME":
                mAdapter = new SubjectAdapter(meSubs, this);
                break;
            case "MPAE":
                mAdapter = new SubjectAdapter(mpaeSubs, this);
                break;
            case "BT":
                mAdapter = new SubjectAdapter(btSubs, this);
                break;
        }
        mRecyclerView.setAdapter(mAdapter);
    }

    private void assignSubjects() {
        eceSubs.add(new Subject("Mathematics I"));
        eceSubs.add(new Subject("Computer Programming"));
        eceSubs.add(new Subject("Electrical and Electronics Engineering"));
        eceSubs.add(new Subject("Physics"));
        eceSubs.add(new Subject("English I"));

        eceSubs.add(new Subject("Mathematics II"));
        eceSubs.add(new Subject("English II"));
        eceSubs.add(new Subject("Electronic Engineering"));
        eceSubs.add(new Subject("Electronics I"));
        eceSubs.add(new Subject("Digital Circuits and Systems"));
        eceSubs.add(new Subject("Electrical Machines"));

        eceSubs.add(new Subject("Mathematics III"));
        eceSubs.add(new Subject("Electronics II"));
        eceSubs.add(new Subject("Network Analysis and Synthesis"));
        eceSubs.add(new Subject("Signal and Systems"));
        eceSubs.add(new Subject("Electromagnetic Field Theory"));

        eceSubs.add(new Subject("Linear Integrated Circuits"));
        eceSubs.add(new Subject("Data Structures"));
        eceSubs.add(new Subject("Transmission lines and Waveguides"));
        eceSubs.add(new Subject("Probability Theory and Communication"));
        eceSubs.add(new Subject("Control Systems"));
        eceSubs.add(new Subject("Digital Signal Processing"));
        eceSubs.add(new Subject("Digital Communication"));
        eceSubs.add(new Subject("Microprocessor and its applications"));
        eceSubs.add(new Subject("Antenna and Wave Propagation"));
        eceSubs.add(new Subject("Microwave Engineering"));
        eceSubs.add(new Subject("VLSI"));
        eceSubs.add(new Subject("Computer Networks"));


        coeSubs.add(new Subject("Mathematics I"));
        coeSubs.add(new Subject("Computer Programming"));
        coeSubs.add(new Subject("Electrical and Electronics Engineering"));
        coeSubs.add(new Subject("Physics"));
        coeSubs.add(new Subject("English I"));

        coeSubs.add(new Subject("Mathematics II"));
        coeSubs.add(new Subject("English-II"));
        coeSubs.add(new Subject("Discrete Structures"));
        coeSubs.add(new Subject("Data Structures"));
        coeSubs.add(new Subject("Digital Logic Design"));
        coeSubs.add(new Subject("Analog and Digital Communication"));
        coeSubs.add(new Subject("Design and Analysis of Algorithms"));
        coeSubs.add(new Subject("Database Management Systems"));
        coeSubs.add(new Subject("Object Orientation"));
        coeSubs.add(new Subject("Computer Architecture and Organization"));
        coeSubs.add(new Subject("Analog Electronics"));
        coeSubs.add(new Subject("Microprocessors"));
        coeSubs.add(new Subject("Software Engineering"));
        coeSubs.add(new Subject("Computer Graphics"));
        coeSubs.add(new Subject("Computer Networking"));
        coeSubs.add(new Subject("Operating Systems"));
        coeSubs.add(new Subject("Theory of Computation"));
        coeSubs.add(new Subject("High Performance Computing"));
        coeSubs.add(new Subject("Compiler Construction"));
        coeSubs.add(new Subject("Modeling and Simulation"));
        coeSubs.add(new Subject("Computer Control Systems"));
        coeSubs.add(new Subject("IT Law and Ethics"));
        coeSubs.add(new Subject("Open Source Technologies"));


        itSubs.add(new Subject("Mathematics I"));
        itSubs.add(new Subject("Computer Programming"));
        itSubs.add(new Subject("Electrical and Electronics Engineering"));
        itSubs.add(new Subject("Physics"));
        itSubs.add(new Subject("English I"));

        itSubs.add(new Subject("Mathematics II"));
        itSubs.add(new Subject("English-II"));
        itSubs.add(new Subject("Chemistry"));
        itSubs.add(new Subject("Object Oriented Techniques"));
        itSubs.add(new Subject("Analog and Digital Communication"));
        itSubs.add(new Subject("Discrete Structure"));

        itSubs.add(new Subject("Mathematics III"));
        itSubs.add(new Subject("Data Structure and Algorithm"));
        itSubs.add(new Subject("Digital Circuits and Systems"));
        itSubs.add(new Subject("Database Management System"));
        itSubs.add(new Subject("Computer Graphics"));
        itSubs.add(new Subject("Probability and Stochastic Processes"));
        itSubs.add(new Subject("Operating System"));
        itSubs.add(new Subject("Computer System Architecture"));
        itSubs.add(new Subject("Computer Networks"));
        itSubs.add(new Subject("Software Engineering"));
        itSubs.add(new Subject("Multimedia & Applications"));
        itSubs.add(new Subject("Theory of Computation"));
        itSubs.add(new Subject("Design And Analysis of Algorithm"));
        itSubs.add(new Subject("Linux/Unix Lab"));
        itSubs.add(new Subject("Internet and Web Engineering"));
        itSubs.add(new Subject("Compiler and Translator Design"));
        itSubs.add(new Subject("Modeling and Simulation"));

    }
}
