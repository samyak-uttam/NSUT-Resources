package com.example.nsutallin1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.Class.Contest;
import com.example.nsutallin1.R;

import java.util.ArrayList;

public class DsAlgoAdapter extends RecyclerView.Adapter<DsAlgoAdapter.DsAlgoViewHolder> {

    private ArrayList<Contest> mContests;
    private Context mContext;

    public DsAlgoAdapter(ArrayList<Contest> mContests, Context mContext) {
        this.mContests = mContests;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DsAlgoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ds_algo_item, parent, false);
        return new DsAlgoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DsAlgoViewHolder holder, int position) {

        final Contest contest = mContests.get(position);

        holder.name.setText(contest.getName());
        holder.startTime.setText(contest.getStartingTime());
        holder.endTime.setText(contest.getEndTime());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(contest.getContestLink()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContests.size();
    }

    public static class DsAlgoViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView startTime;
        TextView endTime;

        View rootView;

        public DsAlgoViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            name = itemView.findViewById(R.id.contest_name);
            startTime = itemView.findViewById(R.id.start_time);
            endTime = itemView.findViewById(R.id.end_time);
        }
    }
}
