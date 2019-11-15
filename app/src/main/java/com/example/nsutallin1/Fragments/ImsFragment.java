package com.example.nsutallin1.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nsutallin1.Activity.CodingActivity;
import com.example.nsutallin1.Activity.NoticesActivity;
import com.example.nsutallin1.Activity.StudentLoginActivity;
import com.example.nsutallin1.Activity.SyllabusActivity;
import com.example.nsutallin1.R;

public class ImsFragment extends Fragment {

    private LinearLayout syllabi, studentLogin, coding;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_ims, container, false);

        syllabi = rootView.findViewById(R.id.syllabus);
        studentLogin = rootView.findViewById(R.id.student_login);
        coding = rootView.findViewById(R.id.coding);

        syllabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SyllabusActivity.class);
                startActivity(intent);
            }
        });

        studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"This feature will be added in the next update!",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getActivity(), StudentLoginActivity.class);
                //startActivity(intent);
            }
        });

        coding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CodingActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
