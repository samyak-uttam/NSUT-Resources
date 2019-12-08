package com.example.nsutallin1.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.R;

public class UpcomingContestsFragment extends Fragment {

    public UpcomingContestsFragment()
    {

    }

    RecyclerView RecyclerView_UpCommingContests;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_upcomming, container, false);


        RecyclerView_UpCommingContests = rootView.findViewById(R.id.rv_upcomming_contest);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView_UpCommingContests.setLayoutManager(linearLayoutManager);

        return rootView;

    }



}
