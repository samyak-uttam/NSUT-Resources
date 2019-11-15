package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nsutallin1.R;

public class CodingActivity extends AppCompatActivity {

    private LinearLayout cpLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding);

        cpLayout = findViewById(R.id.competetive_prog);

        cpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodingActivity.this, DsAlgoActivity.class);
                startActivity(intent);
            }
        });
    }
}
