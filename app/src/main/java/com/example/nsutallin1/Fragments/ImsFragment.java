package com.example.nsutallin1.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nsutallin1.Activity.NoticesActivity;
import com.example.nsutallin1.Activity.StudentLoginActivity;
import com.example.nsutallin1.R;

public class ImsFragment extends Fragment {

    private LinearLayout notices, studentLogin;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_ims, container, false);

        notices = (LinearLayout) rootView.findViewById(R.id.notices_and_circulars);
        studentLogin = (LinearLayout) rootView.findViewById(R.id.student_login);

        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticesActivity.class);
                startActivity(intent);
            }
        });

        studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StudentLoginActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
