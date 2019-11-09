package com.example.nsutallin1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nsutallin1.Adapter.SavedAdapter;
import com.example.nsutallin1.Class.SavedData;
import com.example.nsutallin1.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SavedFragment extends Fragment {

    private ArrayList<SavedData> mSavedData;
    private RecyclerView mRecyclerView;
    private SavedAdapter mAdapter;
    private LinearLayout emptyLayout;

    public SavedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_saved, container, false);

        mSavedData = new ArrayList<>();
        emptyLayout = rootView.findViewById(R.id.empty_layout);

        getFiles();

        mRecyclerView = rootView.findViewById(R.id.saved_rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new SavedAdapter(mSavedData, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void getFiles() {
        mSavedData.clear();
        emptyLayout.setVisibility(View.VISIBLE);
        File listFile[] = getActivity().getExternalFilesDir(null).listFiles();
        if (listFile != null && listFile.length > 0) {
            emptyLayout.setVisibility(View.GONE);
            for (int i = 0; i < listFile.length; i++) {
                String dataName = listFile[i].getName().split("&")[0];
                String dataType = listFile[i].getName().split("&")[1].split(".pdf")[0];
                mSavedData.add(new SavedData(dataName, dataType));
            }

            Collections.sort(mSavedData, new Comparator<SavedData>() {
                @Override
                public int compare(SavedData o1, SavedData o2) {
                    return o1.getDataType().compareTo(o2.getDataType());
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getFiles();
        mAdapter.notifyDataSetChanged();
    }
}
