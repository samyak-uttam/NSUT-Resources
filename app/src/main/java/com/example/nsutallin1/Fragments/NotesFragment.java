package com.example.nsutallin1.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.nsutallin1.Adapter.BranchAdapter;
import com.example.nsutallin1.Adapter.DataAdapter;
import com.example.nsutallin1.Class.Data;
import com.example.nsutallin1.R;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;
import static com.example.nsutallin1.Activity.CollegeActivity.NUM_COLUMNS;

public class NotesFragment extends Fragment implements DataAdapter.ListItemClickListener {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        //clgStuff = rootView.findViewById(R.id.clg_stuff);

        RecyclerView RecyclerView_notes=rootView.findViewById(R.id.rv_notes);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView_notes.setLayoutManager(linearLayoutManager);


        RecyclerView_notes.setHasFixedSize(true);

        ArrayList<Data> data = new ArrayList<>();


        data.add(new Data("ECE", R.drawable.ece));
        data.add(new Data("COE", R.drawable.coe));
        data.add(new Data("IT", R.drawable.it));
        data.add(new Data("ICE", R.drawable.ice));
        data.add(new Data("MPAE", R.drawable.mpae));
        data.add(new Data("ME", R.drawable.me));
        data.add(new Data("BT", R.drawable.bt));
        DataAdapter dataAdapter=new DataAdapter(this,data);

        RecyclerView_notes.setHasFixedSize(true);
        RecyclerView_notes.setAdapter(dataAdapter);

        return rootView;
    }

    @Override
    public void onDataItemClick(int clickedItemIndex) {

    }
}
