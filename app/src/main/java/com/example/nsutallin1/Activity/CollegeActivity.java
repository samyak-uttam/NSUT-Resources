package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.nsutallin1.R;

public class CollegeActivity extends AppCompatActivity {

    private static class BranchViewHolder {
        LinearLayout coeBranch;
        LinearLayout eceBranch;
        LinearLayout itBranch;
        LinearLayout iceBranch;
        LinearLayout mpaeBranch;
        LinearLayout btBranch;
    }

    private static class YearViewHolder {
        LinearLayout yearOne;
        LinearLayout yearTwo;
        LinearLayout yearThree;
        LinearLayout yearFour;
    }

    private static class DataViewHolder {
        LinearLayout booksData;
        LinearLayout papersData;
        LinearLayout notesData;
        LinearLayout practicalsData;
    }

    private CollegeActivity.BranchViewHolder branchViewHolder;

    private CollegeActivity.YearViewHolder yearViewHolder;

    private CollegeActivity.DataViewHolder dataViewHolder;

    private LinearLayout continueLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        branchViewHolder = new CollegeActivity.BranchViewHolder();
        yearViewHolder = new CollegeActivity.YearViewHolder();
        dataViewHolder = new CollegeActivity.DataViewHolder();

        branchViewHolder.coeBranch = (LinearLayout) findViewById(R.id.coe_branch);
        branchViewHolder.eceBranch = (LinearLayout) findViewById(R.id.ece_branch);
        branchViewHolder.itBranch = (LinearLayout) findViewById(R.id.it_branch);
        branchViewHolder.iceBranch = (LinearLayout) findViewById(R.id.ice_branch);
        branchViewHolder.mpaeBranch = (LinearLayout) findViewById(R.id.mpae_branch);
        branchViewHolder.btBranch = (LinearLayout) findViewById(R.id.bt_branch);

        yearViewHolder.yearOne = (LinearLayout) findViewById(R.id.year_1);
        yearViewHolder.yearTwo = (LinearLayout) findViewById(R.id.year_2);
        yearViewHolder.yearThree = (LinearLayout) findViewById(R.id.year_3);
        yearViewHolder.yearFour = (LinearLayout) findViewById(R.id.year_4);

        dataViewHolder.booksData = (LinearLayout) findViewById(R.id.data_books);
        dataViewHolder.papersData = (LinearLayout) findViewById(R.id.data_papers);
        dataViewHolder.notesData = (LinearLayout) findViewById(R.id.data_notes);
        dataViewHolder.practicalsData = (LinearLayout) findViewById(R.id.data_practicals);

        continueLayout = (LinearLayout) findViewById(R.id.continue_button);

        branchViewHolder.coeBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                branchViewHolder.itBranch.setBackgroundDrawable(ContextCompat.getDrawable(CollegeActivity.this, R.drawable.check_black) );
            }
        });

        branchViewHolder.itBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                branchViewHolder.itBranch.setBackgroundResource(R.drawable.check_black);
            }
        });

        branchViewHolder.eceBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                branchViewHolder.eceBranch.setBackgroundResource(R.drawable.check_black);
            }
        });

        branchViewHolder.iceBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                branchViewHolder.iceBranch.setBackgroundResource(R.drawable.check_black);
            }
        });

        branchViewHolder.mpaeBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                branchViewHolder.mpaeBranch.setBackgroundResource(R.drawable.check_black);
            }
        });

        branchViewHolder.btBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                branchViewHolder.btBranch.setBackgroundResource(R.drawable.check_black);
            }
        });
    }
}
