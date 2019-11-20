package com.example.nsutallin1.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nsutallin1.Activity.onNavItemsSelected;
import com.example.nsutallin1.Adapter.BranchAdapter;
import com.example.nsutallin1.Class.Data;
import com.example.nsutallin1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class CollegeFragment extends Fragment implements BranchAdapter.ListItemClickListener {

    public CollegeFragment() {

    }


    private ArrayList<Data> branches;
    public static final int NUM_COLUMNS = 2;
    private String selectedBranch = null;
    private String navSelection = "books";

    private BranchAdapter branchAdapter;
    private RecyclerView RecyclerView_Branch;

    BottomNavigationView bottomNavigationView;


    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_college, container, false);

        bottomNavigationView = rootView.findViewById(R.id.bottom_navigation);

        RecyclerView_Branch = rootView.findViewById(R.id.rv_branch);
        Log.e("well see", String.valueOf(R.id.rv_branch));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, VERTICAL);
        RecyclerView_Branch.setLayoutManager(staggeredGridLayoutManager);


        branches = new ArrayList<>();

        branches.add(new Data("1st SEM", R.drawable.one));
        branches.add(new Data("Jaggi Mathur", R.drawable.maths));
        branches.add(new Data("ECE", R.drawable.ece));
        branches.add(new Data("COE", R.drawable.coe));
        branches.add(new Data("IT", R.drawable.it));
        branches.add(new Data("ICE", R.drawable.ice));
        branches.add(new Data("MPAE", R.drawable.mpae));
        branches.add(new Data("ME", R.drawable.me));
        branches.add(new Data("BT", R.drawable.bt));

        branchAdapter = new BranchAdapter(branches, this);

        RecyclerView_Branch.setAdapter(branchAdapter);


        BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
            Toast bottomNavigationToast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_books:

                        Log.e("nav_books", "You are in books");
                        bottomNavigationToast.setText( "You are in books");
                        bottomNavigationToast.show();
                        navSelection = "books";
                        break;

                    case R.id.nav_praciticals:

                        Log.e("nav_practicals", "You are in practicals");
                        bottomNavigationToast.setText("You are in practicals");
                        bottomNavigationToast.show();
                        navSelection = "practicals";
                        break;

                    case R.id.nav_notes:

                        Log.e("nav_notes", "You are in notes");
                        bottomNavigationToast.setText("You are in notes");
                        bottomNavigationToast.show();
                        navSelection = "notes";
                        break;

                    case R.id.nav_papers:

                        Log.e("nav_papers", "You are in papers");
                        bottomNavigationToast.setText( "You are in papers");
                        bottomNavigationToast.show();
                        navSelection = "papers";
                        break;
                }

                return true;
            }

        };

        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);

        return rootView;
    }

    @Override
    public void onBranchItemClick(int clickedItemIndex) {
        selectedBranch = branches.get(clickedItemIndex).getName();
        if (navSelection != null) {
            Intent intent = new Intent(getActivity(), onNavItemsSelected.class);
            intent.putExtra("branchName", selectedBranch);
            intent.putExtra("navSelectedItem", navSelection);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Please Select Something First!", Toast.LENGTH_SHORT).show();
        }
    }
}