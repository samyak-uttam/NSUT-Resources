package com.educational.nsutresources.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.educational.nsutresources.Adapter.SubjectAdapter;
import com.educational.nsutresources.Class.Subject;
import com.educational.nsutresources.R;

import java.util.ArrayList;

public class onNavItemsSelected extends AppCompatActivity {

    private static String selectedBranch;
    private static String navSelection;
    private SubjectAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private static ArrayList<Subject> yearOneSubs, jaggiMathur, coeSubs, itSubs, eceSubs, iceSubs, meSubs, mpaeSubs, btSubs, electives;

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
        electives = new ArrayList<>();

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
            case "Electives":
                mAdapter = new SubjectAdapter(electives, 9, data, this);

        }
        mRecyclerView.setAdapter(mAdapter);
    }

    private void assignSubjects() {

        yearOneSubs.add(new Subject("Mathematics I"));
        yearOneSubs.add(new Subject("Computer Programming"));
        yearOneSubs.add(new Subject("Electronics and Electrical Engineering"));
        yearOneSubs.add(new Subject("Physics"));
        yearOneSubs.add(new Subject("English"));
        yearOneSubs.add(new Subject("Environment Science and Green Chemistry"));
        yearOneSubs.add(new Subject("Basics of Mechanical Engg"));


        jaggiMathur.add(new Subject("Book I"));
        jaggiMathur.add(new Subject("Book II"));


        eceSubs.add(new Subject("SEM 2"));
        eceSubs.add(new Subject("Mathematics II"));
        eceSubs.add(new Subject("English"));
        eceSubs.add(new Subject("Active Circuit Analysis and Synthesis"));
        eceSubs.add(new Subject("Data Structures and Algorithms"));
        eceSubs.add(new Subject("Electronic Devices and Circuits"));
        eceSubs.add(new Subject("Environment Science and Green Chemistry"));
        eceSubs.add(new Subject("SEM 3"));
        eceSubs.add(new Subject("Signal and Systems"));
        eceSubs.add(new Subject("Microelectronics"));
        eceSubs.add(new Subject("Mathematics for Engineers"));
        eceSubs.add(new Subject("Probability Theory and Random Processes"));
        eceSubs.add(new Subject("Digital Circuits and Systems"));
        eceSubs.add(new Subject("SEM 4"));
        eceSubs.add(new Subject("Electromagnetics"));
        eceSubs.add(new Subject("Machine Learning and AI"));
        eceSubs.add(new Subject("MicroProcessors and Comp Architecture"));
        eceSubs.add(new Subject("Communication Engineering"));
        eceSubs.add(new Subject("SEM 5"));
        eceSubs.add(new Subject("Digital Signal Processing"));
        eceSubs.add(new Subject("Digital Communication"));
        eceSubs.add(new Subject("Microprocessor and its applications"));
        eceSubs.add(new Subject("Antenna and Wave Propagation"));
        eceSubs.add(new Subject("SEM 6"));
        eceSubs.add(new Subject("Microwave Engineering"));
        eceSubs.add(new Subject("VLSI"));
        eceSubs.add(new Subject("Computer Networks"));


        coeSubs.add(new Subject("SEM 2"));
        coeSubs.add(new Subject("Mathematics II"));
        coeSubs.add(new Subject("English"));
        coeSubs.add(new Subject("Discrete Structures"));
        coeSubs.add(new Subject("Data Structures"));
        coeSubs.add(new Subject("Digital Logic Design"));
        coeSubs.add(new Subject("Environment Science and Green Chemistry"));
        coeSubs.add(new Subject("SEM 3"));
        coeSubs.add(new Subject("Design and Analysis of Algorithms"));
        coeSubs.add(new Subject("Database Management Systems"));
        coeSubs.add(new Subject("Web Technology"));
        coeSubs.add(new Subject("Computer Architecture and Organization"));
        coeSubs.add(new Subject("Microprocessors and Microcontrollers"));
        coeSubs.add(new Subject("SEM 4"));
        coeSubs.add(new Subject("Operating Systems"));
        coeSubs.add(new Subject("Theory of Automata & Formal languages"));
        coeSubs.add(new Subject("Software Engineering"));
        coeSubs.add(new Subject("Data Communication"));
        coeSubs.add(new Subject("Probability and Stochastic Processes"));
        coeSubs.add(new Subject("SEM 5"));
        coeSubs.add(new Subject("Theory of Computation"));
        coeSubs.add(new Subject("High Performance Computing"));
        coeSubs.add(new Subject("Compiler Construction"));
        coeSubs.add(new Subject("Modeling and Simulation"));
        coeSubs.add(new Subject("SEM 6"));
        coeSubs.add(new Subject("Computer Control Systems"));
        coeSubs.add(new Subject("IT Law and Ethics"));
        coeSubs.add(new Subject("Open Source Technologies"));


        itSubs.add(new Subject("SEM 2"));
        itSubs.add(new Subject("Mathematics II"));
        itSubs.add(new Subject("English"));
        itSubs.add(new Subject("Discrete Structures"));
        itSubs.add(new Subject("Data Structures"));
        itSubs.add(new Subject("Digital Logic Design"));
        itSubs.add(new Subject("Environment Science and Green Chemistry"));
        itSubs.add(new Subject("SEM 3"));
        itSubs.add(new Subject("Probability and Stochastic Processes"));
        itSubs.add(new Subject("Computer System Organization"));
        itSubs.add(new Subject("Optimization Principles and Techniques"));
        itSubs.add(new Subject("Database Management System"));
        itSubs.add(new Subject("Computer Graphics"));
        itSubs.add(new Subject("SEM 4"));
        itSubs.add(new Subject("Operating System"));
        itSubs.add(new Subject("Design and Analysis of Algorithm"));
        itSubs.add(new Subject("Software Engineering"));
        itSubs.add(new Subject("Computer Networks"));
        itSubs.add(new Subject("Analog and Digital Communication"));
        itSubs.add(new Subject("SEM 5"));
        itSubs.add(new Subject("Multimedia & Applications"));
        itSubs.add(new Subject("Theory of Computation"));
        itSubs.add(new Subject("Design And Analysis of Algorithm"));
        itSubs.add(new Subject("Linux Unix Lab"));
        itSubs.add(new Subject("SEM 6"));
        itSubs.add(new Subject("Internet and Web Engineering"));
        itSubs.add(new Subject("Compiler and Translator Design"));
        itSubs.add(new Subject("Modeling and Simulation"));


        iceSubs.add(new Subject("SEM 2"));
        iceSubs.add(new Subject("Mathematics II"));
        iceSubs.add(new Subject("Physics"));
        iceSubs.add(new Subject("Computer Programming"));
        iceSubs.add(new Subject("Electrical Measurements"));
        iceSubs.add(new Subject("Network Analysis and Synthesis"));
        iceSubs.add(new Subject("Electronics Devices & Circuits"));
        iceSubs.add(new Subject("SEM 3"));
        iceSubs.add(new Subject("Mathematics III"));
        iceSubs.add(new Subject("Data Structures and Algorithms"));
        iceSubs.add(new Subject("Digital Circuits and Systems"));
        iceSubs.add(new Subject("Electronic Instrumentation"));
        iceSubs.add(new Subject("Power Apparatus"));
        iceSubs.add(new Subject("SEM 4"));
        iceSubs.add(new Subject("Database Management System"));
        iceSubs.add(new Subject("Engineering Analysis & Design"));
        iceSubs.add(new Subject("Control System I"));
        iceSubs.add(new Subject("Microprocessor Based System Design"));
        iceSubs.add(new Subject("Sensors and Transducers"));
        iceSubs.add(new Subject("SEM 5"));
        iceSubs.add(new Subject("Microprocessor and Microcontroller"));
        iceSubs.add(new Subject("Process Dynamics and Control"));
        iceSubs.add(new Subject("Analog & Digital Communication"));
        iceSubs.add(new Subject("Control System II"));
        iceSubs.add(new Subject("SEM 6"));
        iceSubs.add(new Subject("Industrial Instrumentation"));
        iceSubs.add(new Subject("Robotics"));
        iceSubs.add(new Subject("Digital Signal Processing"));


        meSubs.add(new Subject("SEM 2"));
        meSubs.add(new Subject("Engineering Mechanics"));
        meSubs.add(new Subject("Engineering Material & Metallurgy"));
        meSubs.add(new Subject("Engineering & Machine Drawing"));
        meSubs.add(new Subject("Computer Programming"));
        meSubs.add(new Subject("Mathematics II"));
        meSubs.add(new Subject("Physics"));
        meSubs.add(new Subject("SEM 3"));
        meSubs.add(new Subject("Manufacturing Processes I"));
        meSubs.add(new Subject("Thermal Engineering I"));
        meSubs.add(new Subject("Numerical Methods and Computation"));
        meSubs.add(new Subject("Strength of Materials"));
        meSubs.add(new Subject("Fluid Mechanics and Machines"));
        meSubs.add(new Subject("SEM 4"));
        meSubs.add(new Subject("Theory of Machines"));
        meSubs.add(new Subject("Manufacturing Processes II"));
        meSubs.add(new Subject("Thermal Engineering II"));
        meSubs.add(new Subject("Industrial Engineering & Management"));
        meSubs.add(new Subject("Control Systems"));
        meSubs.add(new Subject("SEM 5"));
        meSubs.add(new Subject("Industrial Engineering"));
        meSubs.add(new Subject("Refrigeration & Air-Conditioning"));
        meSubs.add(new Subject("Transducers and Measurements"));
        meSubs.add(new Subject("Control Systems"));
        meSubs.add(new Subject("SEM 6"));
        meSubs.add(new Subject("Heat & Mass Transfer"));
        meSubs.add(new Subject("Fluid Systems"));
        meSubs.add(new Subject("Machine Element Design"));
        meSubs.add(new Subject("Mechanical Vibrations"));


        mpaeSubs.add(new Subject("SEM 2"));
        mpaeSubs.add(new Subject("Mathematics II"));
        mpaeSubs.add(new Subject("Physics"));
        mpaeSubs.add(new Subject("Computer Programming"));
        mpaeSubs.add(new Subject("Engineering Mechanics"));
        mpaeSubs.add(new Subject("Mechanical Sciences"));
        mpaeSubs.add(new Subject("Engineering Materials & Metallurgy"));
        mpaeSubs.add(new Subject("SEM 3"));
        mpaeSubs.add(new Subject("Microprocessors and Microcontrollers"));
        mpaeSubs.add(new Subject("Strength of Materials"));
        mpaeSubs.add(new Subject("Numerical Methods and Computation"));
        mpaeSubs.add(new Subject("Manufacturing Processes I"));
        mpaeSubs.add(new Subject("Mechanical Drawing"));
        mpaeSubs.add(new Subject("SEM 4"));
        mpaeSubs.add(new Subject("Kinematics Dynamics of Machines"));
        mpaeSubs.add(new Subject("Mechanics of Solids"));
        mpaeSubs.add(new Subject("Transducers and Measurements"));
        mpaeSubs.add(new Subject("Manufacturing Processes II"));
        mpaeSubs.add(new Subject("Science of Materials"));
        mpaeSubs.add(new Subject("SEM 5"));
        mpaeSubs.add(new Subject("Machine Tools, CNC and Automation"));
        mpaeSubs.add(new Subject("Metrology and Quality Control"));
        mpaeSubs.add(new Subject("Tool Design"));
        mpaeSubs.add(new Subject("Operations Research"));
        mpaeSubs.add(new Subject("SEM 6"));
        mpaeSubs.add(new Subject("Geometric Modeling"));
        mpaeSubs.add(new Subject("Applied Plasticity"));
        mpaeSubs.add(new Subject("Mechanical Design"));
        mpaeSubs.add(new Subject("SEM 7"));
        mpaeSubs.add(new Subject("Product Design"));
        mpaeSubs.add(new Subject("Modern Methods of Manufacturing"));


        btSubs.add(new Subject("SEM 2"));
        btSubs.add(new Subject("Mathematics II"));
        btSubs.add(new Subject("Physics"));
        btSubs.add(new Subject("Physics of Materials"));
        btSubs.add(new Subject("Computer Programming"));
        btSubs.add(new Subject("Advance Chemistry"));
        btSubs.add(new Subject("Introduction to Biotechnology"));
        btSubs.add(new Subject("SEM 3"));
        btSubs.add(new Subject("Biochemistry"));
        btSubs.add(new Subject("Microbiology"));
        btSubs.add(new Subject("Cell Biology"));
        btSubs.add(new Subject("Data Structure And Algorithms"));
        btSubs.add(new Subject("Chemical Enggineering Principles"));
        btSubs.add(new Subject("SEM 4"));
        btSubs.add(new Subject("Structural Biology"));
        btSubs.add(new Subject("Bioanalytical Techniques"));
        btSubs.add(new Subject("Molecular Biology"));
        btSubs.add(new Subject("Genetics"));
        btSubs.add(new Subject("Immunology"));
        btSubs.add(new Subject("SEM 5"));
        btSubs.add(new Subject("Recombinant DNA Technology"));
        btSubs.add(new Subject("Structural Biology"));
        btSubs.add(new Subject("Fundamentals of Biochemical Engg."));
        btSubs.add(new Subject("Enzymology"));
        btSubs.add(new Subject("SEM 6"));
        btSubs.add(new Subject("Bioprocess Technology"));
        btSubs.add(new Subject("Plant and Animal Technology"));
        btSubs.add(new Subject("Downstream Processing"));

        electives.add(new Subject("Corporate Social Responsibility"));
        electives.add(new Subject("Introduction to Indian Society"));
        electives.add(new Subject("Entrepreneurship"));
        electives.add(new Subject("Yoga"));
        electives.add(new Subject("Financial Literacy"));
        electives.add(new Subject("Analog filter design"));
        electives.add(new Subject("Biological computing"));
        electives.add(new Subject("Image Processing"));
        electives.add(new Subject("BICMOS"));
    }
}
