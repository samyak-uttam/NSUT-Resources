package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nsutallin1.Adapter.BranchAdapter;
import com.example.nsutallin1.Adapter.DataAdapter;
import com.example.nsutallin1.Adapter.YearAdapter;
import com.example.nsutallin1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static androidx.recyclerview.widget.LinearLayoutManager.*;
import static com.example.nsutallin1.R.color.*;

public class CollegeActivity extends AppCompatActivity implements BranchAdapter.ListItemClickListener,YearAdapter.ListItemClickListener,DataAdapter.ListItemClickListener {

    private static ArrayList<String> branchNames=new ArrayList<>();
    private static ArrayList<Integer> branchImages=new ArrayList<>();

    private static ArrayList<String> yearNames=new ArrayList<>();
    private static ArrayList<Integer> yearImages=new ArrayList<>();

    private static ArrayList<String> dataNames=new ArrayList<>();
    private static ArrayList<Integer> dataImages=new ArrayList<>();

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

    private final int NUM_BRANCHES=7;
    private final int NUM_YEAR=4;
    private final int NUM_DATA=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        for (int position=0;position<NUM_BRANCHES;position++)
        {
            if (position==0)
            {
                branchNames.add("COE");
                branchImages.add(R.drawable.coe);
            }

            else if (position==1) {

                branchNames.add("IT");
                branchImages.add(R.drawable.it);
            }


            else if (position==2) {

                branchNames.add("ECE");
                branchImages.add(R.drawable.ece);
            }


            else if (position==3) {

                branchNames.add("ICE");
                branchImages.add(R.drawable.ice);
            }


            else if (position==4) {

                branchNames.add("MPAE");
                branchImages.add(R.drawable.mpae);
            }


            else if (position==5) {

                branchImages.add(R.drawable.me);
                branchNames.add("ME");
            }

            else if (position==6) {

                branchImages.add(R.drawable.bt);
                branchNames.add("BT");

            }

            for (int position1=0;position1<NUM_YEAR;position1++)
            {
                if (position1==0)
                {
                    yearNames.add(Integer.toString(1));
                    yearImages.add(R.drawable.year_placeholder);
                }

                else if (position1==1)
                {
                    yearNames.add(Integer.toString(2));
                    yearImages.add(R.drawable.year_placeholder);
                }

                else if (position1==2)
                {
                    yearNames.add(Integer.toString(3));
                    yearImages.add(R.drawable.year_placeholder);
                }

                else if (position1==3)
                {
                    yearNames.add(Integer.toString(4));
                    yearImages.add(R.drawable.year_placeholder);
                }
            }

            for (int position2=0;position2<NUM_DATA;position2++)
            {
                if (position2==0)
                {
                    dataNames.add("Books");
                    dataImages.add(R.drawable.notes_black);
                }

                else if (position2==1)
                {
                    dataNames.add("Papers");
                    dataImages.add(R.drawable.pages_black);
                }

                else if (position2==2)
                {
                    dataNames.add("Notes");
                    dataImages.add(R.drawable.notes_black);
                }

                else if (position2==3)
                {
                    dataNames.add("Practicals");
                    dataImages.add(R.drawable.pages_black);
                }
            }
        }
        initRecyclerViewBranch();
        initRecyclerViewYear();
        initRecyclerViewData();

        map = new HashMap<>();

        final ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

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


        continueLayout = (LinearLayout) findViewById(R.id.continue_button);

        oddSem = (CheckBox) findViewById(R.id.odd_sem);
        evenSem = (CheckBox) findViewById(R.id.even_sem);

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

    }

    @Override
    public void onBranchItemClick(int clickedItemIndex)
    {
        selectedBranch=branchNames.get(clickedItemIndex);
        Toast.makeText(this,Integer.toString(clickedItemIndex),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onYearItemClick(int clickedItemIndex)
    {
        int year=Integer.valueOf(yearNames.get(clickedItemIndex));

        if (year==1)
        {
            selectedSem=0;
        }

        else if (year==2)
        {
            selectedSem=2;
        }

        else if (year==3)
        {
            selectedSem=4;
        }

        else if (year==4)
        {
            selectedSem=6;
        }
    }

    @Override
    public void onDataItemClick(int clickedItemIndex) {
        Toast.makeText(this,Integer.toString(clickedItemIndex),Toast.LENGTH_SHORT).show();

        selectedData=dataNames.get(clickedItemIndex);
    }

    private void initRecyclerViewData()
    {
        RecyclerView_Data=findViewById(R.id.rv_data);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, HORIZONTAL,false);
        RecyclerView_Data.setLayoutManager(linearLayoutManager2);

        RecyclerView_Data.setHasFixedSize(true);
        dataAdapter=new DataAdapter(NUM_DATA,this,yearNames,yearImages);
        RecyclerView_Data.setAdapter(dataAdapter);

    }

    private void initRecyclerViewYear() {
        RecyclerView_Year=findViewById(R.id.rv_year);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, HORIZONTAL,false);
        RecyclerView_Year.setLayoutManager(linearLayoutManager1);

        RecyclerView_Year.setHasFixedSize(true);
        yearAdapter=new YearAdapter(NUM_YEAR,this,yearNames,yearImages);
        RecyclerView_Year.setAdapter(yearAdapter);

    }

    private void initRecyclerViewBranch() {
        RecyclerView_Branch=findViewById(R.id.rv_branch);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, HORIZONTAL,false);
        RecyclerView_Branch.setLayoutManager(linearLayoutManager);

        RecyclerView_Branch.setHasFixedSize(true);
        branchAdapter=new BranchAdapter(NUM_BRANCHES, (BranchAdapter.ListItemClickListener) this,branchNames,branchImages);
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
