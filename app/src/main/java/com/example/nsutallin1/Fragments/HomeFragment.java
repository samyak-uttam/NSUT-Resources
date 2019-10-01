package com.example.nsutallin1.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nsutallin1.CollegeActivity;
import com.example.nsutallin1.NoticesActivity;
import com.example.nsutallin1.R;

public class HomeFragment extends Fragment {

    LinearLayout clgStuff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        clgStuff = (LinearLayout) rootView.findViewById(R.id.clg_stuff);
        clgStuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CollegeActivity.class);
                startActivity(intent);
            }
        });

        ImageView noticesImage = (ImageView) rootView.findViewById(R.id.notices_and_circulars);
        noticesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticesActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
