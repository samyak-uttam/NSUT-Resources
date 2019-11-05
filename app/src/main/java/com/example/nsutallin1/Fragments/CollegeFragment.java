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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nsutallin1.Activity.onNavItemsSelected;
import com.example.nsutallin1.Adapter.BranchAdapter;
import com.example.nsutallin1.Class.Data;
import com.example.nsutallin1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class CollegeFragment extends Fragment implements BranchAdapter.ListItemClickListener {

    public CollegeFragment() {

    }


    private ArrayList<Data> branches, years, data;
    public static final int NUM_COLUMNS = 2;
    private Map<String, String> map;
    private String selectedBranch = null;

    private BranchAdapter branchAdapter;
    private RecyclerView RecyclerView_Branch;

    String navSelection="books";

    BottomNavigationView bottomNavigationView;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_college, container, false);

        bottomNavigationView = rootView.findViewById(R.id.bottom_navigation);

        RecyclerView_Branch = rootView.findViewById(R.id.rv_branch);
        Log.e("well see", String.valueOf(R.id.rv_branch));
        //bottomNavigationView.setOnNavigationItemSelectedListener(navListner);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, VERTICAL);
        RecyclerView_Branch.setLayoutManager(staggeredGridLayoutManager);


        branches = new ArrayList<>();
        years = new ArrayList<>();
        data = new ArrayList<>();

        branches.add(new Data("1st SEM", R.drawable.one));
        branches.add(new Data("ECE", R.drawable.ece));
        branches.add(new Data("COE", R.drawable.coe));
        branches.add(new Data("IT", R.drawable.it));
        branches.add(new Data("ICE", R.drawable.ice));
        branches.add(new Data("MPAE", R.drawable.mpae));
        branches.add(new Data("ME", R.drawable.me));
        branches.add(new Data("BT", R.drawable.bt));

        years.add(new Data("1", R.drawable.year_placeholder));
        years.add(new Data("2", R.drawable.year_placeholder));
        years.add(new Data("3", R.drawable.year_placeholder));
        years.add(new Data("4", R.drawable.year_placeholder));

        data.add(new Data("Books", R.drawable.pages_black));
        data.add(new Data("Notes", R.drawable.notes_black));
        data.add(new Data("Papers", R.drawable.pages_black));
        data.add(new Data("Practicals", R.drawable.notes_black));

        //initRecyclerViewYear();
        //initRecyclerViewData();
        branchAdapter = new BranchAdapter(branches, this);

        RecyclerView_Branch.setAdapter(branchAdapter);

        BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_books:
                        Log.e("nav_books", "You are in books");
                        Toast.makeText(getContext(), "You are in books", Toast.LENGTH_SHORT).show();
                        navSelection = "books";
                        break;

                    case R.id.nav_praciticals:
                        Log.e("nav_notes", "You are in practicals");
                        Toast.makeText(getContext(), "You are in practicals", Toast.LENGTH_SHORT).show();
                        navSelection = "practicals";
                        break;

                    case R.id.nav_notes:
                        Log.e("nav_notes", "You are in notes");
                        Toast.makeText(getContext(), "You are in notes", Toast.LENGTH_SHORT).show();
                        navSelection = "notes";
                        break;

                    case R.id.nav_papers:
                        Log.e("nav_papers", "You are in papers");
                        Toast.makeText(getContext(), "You are in papers", Toast.LENGTH_SHORT).show();
                        navSelection = "papers";
                        break;
                }

                return true;
            }

        };

        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);


        map = new HashMap<>();

        map.put("ECE1Books", "16kCUSPIblbCDRyiIE__sqHjHsr1O_LWn");
        map.put("ECE1Notes", "1kWCs1rdKoWoXGdVD-0XCyMmJQR4QaacF");
        map.put("ECE1Practicals", "1nzx3ZuGQSnM-v-mA7wANEUYnOSBfhc7s");
        map.put("ECE2Books", "1C6KBeOHnK3jK_VX0qiH98zbcOjVdAA7Z");
        map.put("ECE2Notes", "1BMnsQDY6oydKc34JxUlvRU3rzkdWRhKu");
        map.put("ECE2Papers", "1-bbTx-Gnb9mIiHUC2FTH3rUo4rv-E6Iv");
        map.put("ECE2Practicals", "16KsqtV6LupiBecqClrx75fCQmLXXJP8a");
        map.put("ECE3Books", "15_PcxLYl5ivKpn68E4Av8IGTZMhNKBZc");
        map.put("ECE3Notes", "170-u1m7X2HeO3iswap3BosQ1DNCzIICT");
        map.put("ECE3Papers", "1ggggAaffbpIMkPhPrVrngzqAPJndk0uD");
        map.put("ECE3Practicals", "1J8w2MGWfibzGk-8MqnfVF-cn0hGdTt5D");

        return rootView;

        /*continueLayout = findViewById(R.id.continue_button);

        oddSem = findViewById(R.id.odd_sem);
        evenSem = findViewById(R.id.even_sem);

        oddSem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(oddSem.isChecked() && evenSem.isChecked())
                    evenSem.setChecked(false);
            }
        });

        evenSem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(oddSem.isChecked() && evenSem.isChecked())
                    oddSem.setChecked(false);
            }
        });

        continueLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

         */

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

   /* @Override
    public void onYearItemClick(int clickedItemIndex)
    {
        int year = Integer.valueOf(years.get(clickedItemIndex).getName());
        if (year == 1) {
            selectedSem = 0;

        } else if (year == 2) {
            selectedSem = 2;

        } else if (year == 3)
        {
            selectedSem = 4;

        } else if (year == 4) {
            selectedSem = 6;
        }
    }


    private void getData() {

        if(selectedBranch == null) {
            Toast.makeText(getContext(), "Please Select a branch", Toast.LENGTH_SHORT).show();
            return;
        } else if(selectedSem == -1) {
            Toast.makeText(getContext(), "Please Select a year", Toast.LENGTH_SHORT).show();
            return;
        } else if(!oddSem.isChecked() && !evenSem.isChecked()) {
            Toast.makeText(getContext(), "Please Select a semester", Toast.LENGTH_SHORT).show();
            return;
        } else if(selectedData == null) {
            Toast.makeText(getContext(), "Please Select a data", Toast.LENGTH_SHORT).show();
            return;
        }

        if(oddSem.isChecked()) {
            selectedSem += 1;
        } else {
            selectedSem += 2;
        }

        String field = selectedBranch + selectedSem + selectedData;

        if(map.containsKey(field)) {
            String folderID = map.get(field);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://drive.google.com/drive/folders/" + folderID));
            startActivity(intent);
        } else {
            //Toast.makeText(this, "This feature will be included in our next update.", Toast.LENGTH_SHORT).show();
        }


    }*/
}
