package com.educational.nsutresources.Fragments;

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

import com.educational.nsutresources.Activity.onNavItemsSelected;
import com.educational.nsutresources.Adapter.BranchAdapter;
import com.educational.nsutresources.Class.Data;
import com.educational.nsutresources.R;
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
    Toast bottomNavigationToast;


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


            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_books:

                        Log.e("nav_books", "You are in books");
                        bottomNavigationToast.makeText(getContext(), "You are in books", Toast.LENGTH_SHORT).show();
                        navSelection = "books";
                        break;

                    case R.id.nav_praciticals:

                        Log.e("nav_practicals", "You are in practicals");
                        bottomNavigationToast.makeText(getContext(), "You are in practicals", Toast.LENGTH_SHORT).show();
                        navSelection = "practicals";
                        break;

                    case R.id.nav_notes:

                        Log.e("nav_notes", "You are in notes");
                        bottomNavigationToast.makeText(getContext(), "You are in notes", Toast.LENGTH_SHORT).show();
                        navSelection = "notes";
                        break;

                    case R.id.nav_papers:

                        Log.e("nav_papers", "You are in papers");
                        bottomNavigationToast.makeText(getContext(), "You are in papers", Toast.LENGTH_SHORT).show();
                        navSelection = "papers";
                        break;

                        default:
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