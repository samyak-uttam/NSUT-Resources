package com.example.nsutallin1.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RunningContestAdapter extends RecyclerView.Adapter<RunningContestAdapter.RunningContestViewHolder> {
    @NonNull
    @Override
    public RunningContestAdapter.RunningContestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RunningContestAdapter.RunningContestViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RunningContestViewHolder extends RecyclerView.ViewHolder {
        public RunningContestViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
