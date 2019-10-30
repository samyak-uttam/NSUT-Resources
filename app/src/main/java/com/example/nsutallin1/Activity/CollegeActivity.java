package com.example.nsutallin1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nsutallin1.Adapter.BranchAdapter;
import com.example.nsutallin1.Adapter.DataAdapter;
import com.example.nsutallin1.Adapter.YearAdapter;
import com.example.nsutallin1.Class.Data;
import com.example.nsutallin1.Fragments.HomeFragment;
import com.example.nsutallin1.Fragments.NotesFragment;
import com.example.nsutallin1.Fragments.PapersFragment;
import com.example.nsutallin1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class CollegeActivity extends AppCompatActivity implements BranchAdapter.ListItemClickListener, YearAdapter.ListItemClickListener, DataAdapter.ListItemClickListener {


    private ArrayList<Data> branches, years, data;
    public static final int NUM_COLUMNS=2;
    private Map<String, String> map;
    private String selectedBranch = null, selectedData = null;
    private int selectedSem = -1;
    LinearLayout continueLayout;

    private BranchAdapter branchAdapter;
    private RecyclerView RecyclerView_Branch;

    private YearAdapter yearAdapter;
    private RecyclerView RecyclerView_Year;

    private DataAdapter dataAdapter;
    private RecyclerView RecyclerView_Data;

    private CheckBox oddSem, evenSem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        branches = new ArrayList<>();
        years = new ArrayList<>();
        data = new ArrayList<>();


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

        initRecyclerViewBranch();
        initNavigationBar();
        //initRecyclerViewYear();
        //initRecyclerViewData();

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

    private void initNavigationBar() {
        BottomNavigationView bottomNav= findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListner);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner=new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(MenuItem item)
        {
          switch (item.getItemId())
          {

              case R.id.nav_home:

                  break;
              case R.id.nav_praciticals:
                  getSupportFragmentManager().beginTransaction().replace(R.id.framgment_container,
                          new HomeFragment()).commit();
                  break;
              case R.id.nav_notes:
                  getSupportFragmentManager().beginTransaction().replace(R.id.framgment_container,
                          new NotesFragment()).commit();
                  break;
              case R.id.nav_papers:
                  getSupportFragmentManager().beginTransaction().replace(R.id.framgment_container,
                          new PapersFragment()).commit();
                  break;
          }

          return true;
        }

    };

    @Override
    public void onBranchItemClick(int clickedItemIndex)
    {
        selectedBranch = branches.get(clickedItemIndex).getName();
        Toast.makeText(this,Integer.toString(clickedItemIndex),Toast.LENGTH_SHORT).show();
    }

    @Override
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

    @Override
    public void onDataItemClick(int clickedItemIndex) {
        Toast.makeText(this,Integer.toString(clickedItemIndex),Toast.LENGTH_SHORT).show();

        selectedData = data.get(clickedItemIndex).getName();
    }


    private void initRecyclerViewBranch() {
        RecyclerView_Branch=findViewById(R.id.rv_branch);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, VERTICAL);
        RecyclerView_Branch.setLayoutManager(staggeredGridLayoutManager);

       // RecyclerView_Branch.setHasFixedSize(true);
        branchAdapter=new BranchAdapter(this, branches);
        RecyclerView_Branch.setAdapter(branchAdapter);

    }

    private void getData() {

        if(selectedBranch == null) {
            Toast.makeText(this, "Please Select a branch", Toast.LENGTH_SHORT).show();
            return;
        } else if(selectedSem == -1) {
            Toast.makeText(this, "Please Select a year", Toast.LENGTH_SHORT).show();
            return;
        } else if(!oddSem.isChecked() && !evenSem.isChecked()) {
            Toast.makeText(this, "Please Select a semester", Toast.LENGTH_SHORT).show();
            return;
        } else if(selectedData == null) {
            Toast.makeText(this, "Please Select a data", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "This feature will be included in our next update.", Toast.LENGTH_SHORT).show();
        }


    }
}
