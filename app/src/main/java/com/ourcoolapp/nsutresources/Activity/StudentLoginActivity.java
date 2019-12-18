package com.ourcoolapp.nsutresources.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ourcoolapp.nsutresources.R;

public class StudentLoginActivity extends AppCompatActivity {

    private EditText userIDET, passET, secCodeET;
    private String userID, pass, secCode;
    private Button loginBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        userIDET = findViewById(R.id.user_id_et);
        passET = findViewById(R.id.pass_et);
        secCodeET = findViewById(R.id.sec_code_et);

        loginBTN = findViewById(R.id.login_btn);

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = userIDET.getText().toString();
                pass = passET.getText().toString();
                secCode = secCodeET.getText().toString();
                login();
            }
        });
    }

    private void login() {

    }
}
