package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nsutallin1.R;

public class ImsActivity extends AppCompatActivity {

    private LinearLayout notices, studentLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ims);

        notices = (LinearLayout) findViewById(R.id.notices_and_circulars);
        studentLogin = (LinearLayout) findViewById(R.id.student_login);

        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImsActivity.this, NoticesActivity.class);
                startActivity(intent);
            }
        });

        studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImsActivity.this, StudentLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
