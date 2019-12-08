package com.example.nsutallin1.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nsutallin1.R;



public class RunningContestsFragment extends Fragment {

    RecyclerView RecyclerView_runningContests;

    public RunningContestsFragment() {

    }


    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_running, container, false);


        RecyclerView_runningContests = rootView.findViewById(R.id.rv_running_contest);
        RecyclerView_runningContests.setLayoutManager(new LinearLayoutManager(getContext()));


        return rootView;

    }
}
