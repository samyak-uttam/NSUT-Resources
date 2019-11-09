package com.example.nsutallin1.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nsutallin1.Adapter.SubjectAdapter;
import com.example.nsutallin1.Class.Subject;
import com.example.nsutallin1.R;

import java.util.ArrayList;

public class onNavItemsSelected extends AppCompatActivity {

    private static String selectedBranch;
    private static String navSelection;
    private SubjectAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private static ArrayList<Subject> yearOneSubs, jaggiMathur, coeSubs, itSubs, eceSubs, iceSubs, meSubs, mpaeSubs, btSubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.on_nav_items_selected);

        yearOneSubs = new ArrayList<>();
        jaggiMathur = new ArrayList<>();
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

        String[] data = new String[2];
        data[0] = selectedBranch;
        data[1] = navSelection;

        switch (selectedBranch) {
            case "1st SEM":
                mAdapter = new SubjectAdapter(yearOneSubs, 0, data,this);
                break;
            case "Jaggi Mathur":
                mAdapter = new SubjectAdapter(jaggiMathur, 1, data,this);
                break;
            case "COE":
                mAdapter = new SubjectAdapter(coeSubs,2, data,this);
                break;
            case "IT":
                mAdapter = new SubjectAdapter(itSubs,3, data,this);
                break;
            case "ECE":
                mAdapter = new SubjectAdapter(eceSubs,4, data,this);
                break;
            case "ICE":
                mAdapter = new SubjectAdapter(iceSubs,5, data,this);
                break;
            case "ME":
                mAdapter = new SubjectAdapter(meSubs,6, data,this);
                break;
            case "MPAE":
                mAdapter = new SubjectAdapter(mpaeSubs,7, data,this);
                break;
            case "BT":
                mAdapter = new SubjectAdapter(btSubs,8, data,this);
                break;
        }
        mRecyclerView.setAdapter(mAdapter);
    }

    private void assignSubjects() {

        yearOneSubs.add(new Subject("Mathematics I"));
        yearOneSubs.add(new Subject("Computer Programming"));
        yearOneSubs.add(new Subject("Electrical and Electronics Engineering"));
        yearOneSubs.add(new Subject("Physics"));
        yearOneSubs.add(new Subject("English I"));

        jaggiMathur.add(new Subject("Book I"));
        jaggiMathur.add(new Subject("Book II"));

        eceSubs.add(new Subject("Mathematics II"));
        eceSubs.add(new Subject("English II"));
        eceSubs.add(new Subject("Electronic Engineering Materials"));
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


        coeSubs.add(new Subject("Mathematics II"));
        coeSubs.add(new Subject("English II"));
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


        itSubs.add(new Subject("Mathematics II"));
        itSubs.add(new Subject("English II"));
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


        iceSubs.add(new Subject("Mathematics II"));
        iceSubs.add(new Subject("English II"));
        iceSubs.add(new Subject("Physics of Material"));
        iceSubs.add(new Subject("Applied Mechanics"));
        iceSubs.add(new Subject("Signal and Systems"));
        iceSubs.add(new Subject("Power Apparatus"));
        iceSubs.add(new Subject("Electronic Instrumentation"));
        iceSubs.add(new Subject("Electronics"));
        iceSubs.add(new Subject("Engineering Graphics"));
        iceSubs.add(new Subject("Data Structures"));
        iceSubs.add(new Subject("Chemistry"));
        iceSubs.add(new Subject("Mathematics III"));
        iceSubs.add(new Subject("Control System I"));
        iceSubs.add(new Subject("Transducer and Measurement"));
        iceSubs.add(new Subject("Industrial Electronics"));
        iceSubs.add(new Subject("Digital Circuits and Systems"));
        iceSubs.add(new Subject("Microprocessor and Microcontroller"));
        iceSubs.add(new Subject("Process Dynamics and Control"));
        iceSubs.add(new Subject("Analog & Digital Communication"));
        iceSubs.add(new Subject("Control System II"));
        iceSubs.add(new Subject("Industrial Instrumentation"));
        iceSubs.add(new Subject("Robotics"));
        iceSubs.add(new Subject("Digital Signal Processing"));


        meSubs.add(new Subject("Mathematics II"));
        meSubs.add(new Subject("English II"));
        meSubs.add(new Subject("Chemistry"));
        meSubs.add(new Subject("Engineering Mechanics"));
        meSubs.add(new Subject("Workshop Technology"));
        meSubs.add(new Subject("Engineering Graphics"));
        meSubs.add(new Subject("Machine Drawing"));
        meSubs.add(new Subject("Manufacturing Processes I"));
        meSubs.add(new Subject("Mathematics III"));
        meSubs.add(new Subject("Thermal Engineering"));
        meSubs.add(new Subject("Science of Materials"));
        meSubs.add(new Subject("Kinematics Dynamics of Machines"));
        meSubs.add(new Subject("Mechanics of Solids"));
        meSubs.add(new Subject("Fluid Mechanics"));
        meSubs.add(new Subject("Manufacturing Processes II"));
        meSubs.add(new Subject("Management of Manufacturing Systems"));
        meSubs.add(new Subject("Industrial Engineering"));
        meSubs.add(new Subject("Refrigeration & Air-Conditioning"));
        meSubs.add(new Subject("Transducers and Measurements"));
        meSubs.add(new Subject("Control Systems"));
        meSubs.add(new Subject("Heat & Mass Transfer"));
        meSubs.add(new Subject("Fluid Systems"));
        meSubs.add(new Subject("Machine Element Design"));
        meSubs.add(new Subject("Mechanical Vibrations"));
        meSubs.add(new Subject("Product Design"));


        mpaeSubs.add(new Subject("Mathematics II"));
        mpaeSubs.add(new Subject("English II"));
        mpaeSubs.add(new Subject("Chemistry"));
        mpaeSubs.add(new Subject("Engineering Mechanics"));
        mpaeSubs.add(new Subject("Workshop Technology"));
        mpaeSubs.add(new Subject("Engineering Graphics"));
        mpaeSubs.add(new Subject("Machine Drawing"));
        mpaeSubs.add(new Subject("Manufacturing Processes I"));
        mpaeSubs.add(new Subject("Mechanical Sciences"));
        mpaeSubs.add(new Subject("Control Systems"));
        mpaeSubs.add(new Subject("Mathematics III"));
        mpaeSubs.add(new Subject("Kinematics Dynamics of Machines"));
        mpaeSubs.add(new Subject("Mechanics of Solids"));
        mpaeSubs.add(new Subject("Transducers and Measurements"));
        mpaeSubs.add(new Subject("Manufacturing Processes II"));
        mpaeSubs.add(new Subject("Science of Materials"));
        mpaeSubs.add(new Subject("Machine Tools, CNC and Automation"));
        mpaeSubs.add(new Subject("Metrology and Quality Control"));
        mpaeSubs.add(new Subject("Tool Design"));
        mpaeSubs.add(new Subject("Operations Research"));
        mpaeSubs.add(new Subject("Geometric Modeling"));
        mpaeSubs.add(new Subject("Applied Plasticity"));
        mpaeSubs.add(new Subject("Mechanical Design"));
        mpaeSubs.add(new Subject("Product Design"));
        mpaeSubs.add(new Subject("Modern Methods of Manufacturing"));


        btSubs.add(new Subject("Mathematics II"));
        btSubs.add(new Subject("English II"));
        btSubs.add(new Subject("Physics of Material"));
        btSubs.add(new Subject("Advance Chemistry"));
        btSubs.add(new Subject("Biostatistics"));
        btSubs.add(new Subject("Introduction to Biotechnology"));
        btSubs.add(new Subject("Biochemistry"));
        btSubs.add(new Subject("Microbiology"));
        btSubs.add(new Subject("Cell Biology"));
        btSubs.add(new Subject("Data Structure And Algorithms"));
        btSubs.add(new Subject("Chemical Enggineering Principles"));
        btSubs.add(new Subject("Methods & Instrumentation In Biotechnology"));
        btSubs.add(new Subject("Molecular Biology"));
        btSubs.add(new Subject("Immunology"));
        btSubs.add(new Subject("Database Management ystems"));
        btSubs.add(new Subject("Genetics"));
        btSubs.add(new Subject("Recombinant DNA Technology"));
        btSubs.add(new Subject("Structural Biology"));
        btSubs.add(new Subject("Fundamentals of Biochemical Engg."));
        btSubs.add(new Subject("Enzymology"));
        btSubs.add(new Subject("Bioprocess Technology"));
        btSubs.add(new Subject("Plant and Animal Technology"));
        btSubs.add(new Subject("Downstream Processing"));
    }
}
