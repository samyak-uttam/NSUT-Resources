package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nsutallin1.R;

import java.util.HashMap;
import java.util.Map;
import static com.example.nsutallin1.R.color.*;

public class CollegeActivity extends AppCompatActivity {

    private Map<String, String> map;

    private String selectedBranch = null, selectedData = null;
    private int selectedSem = -1;

    private CheckBox oddSem, evenSem;

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

        map = new HashMap<String, String>();

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

        oddSem = (CheckBox) findViewById(R.id.odd_sem);
        evenSem = (CheckBox) findViewById(R.id.even_sem);

        branchViewHolder.coeBranch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                selectedBranch = "COE";

                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.coeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(white));


                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(GrayGoose));

            }
        });

        branchViewHolder.itBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBranch = "IT";

                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(white));


                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.coeBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(GrayGoose));
            }
        });

        branchViewHolder.eceBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBranch = "ECE";

                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.coeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(white));

                branchViewHolder.coeBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(GrayGoose));
            }
        });

        branchViewHolder.iceBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBranch = "ICE";

                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.coeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(white));

                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.coeBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(GrayGoose));
            }
        });

        branchViewHolder.mpaeBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBranch = "MPAE";

                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.coeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(white));

                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.coeBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(GrayGoose));
            }
        });

        branchViewHolder.btBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBranch = "BT";

                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.coeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(white));
                branchViewHolder.btBranch.setBackgroundColor(getResources().getColor(white));

                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.itBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.iceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.mpaeBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.eceBranch.setBackgroundColor(getResources().getColor(GrayGoose));
                branchViewHolder.coeBranch.setBackgroundColor(getResources().getColor(GrayGoose));
            }
        });


        yearViewHolder.yearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSem = 0;
            }
        });

        yearViewHolder.yearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSem = 2;
            }
        });

        yearViewHolder.yearThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSem = 4;
            }
        });

        yearViewHolder.yearFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSem = 6;
            }
        });


        dataViewHolder.booksData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedData = "Books";
            }
        });

        dataViewHolder.notesData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedData = "Notes";
            }
        });

        dataViewHolder.papersData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedData = "Papers";
            }
        });

        dataViewHolder.practicalsData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedData = "Practicals";
            }
        });

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
