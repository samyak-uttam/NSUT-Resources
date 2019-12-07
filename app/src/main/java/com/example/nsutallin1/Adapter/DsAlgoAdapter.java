package com.example.nsutallin1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.Activity.ContestDetailsActivity;
import com.example.nsutallin1.Class.Contest;
import com.example.nsutallin1.Class.Data;
import com.example.nsutallin1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        holder.siteImage.setImageResource(contest.getImgResId());
        holder.name.setText(contest.getName());

        final Date date = new Date(contest.getStartingTime());
        holder.startTime.setText(formatDate(date) + ", " + formatTime(date));

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ContestDetailsActivity.class);
                intent.putExtra("contest_name",contest.getName());
                intent.putExtra("contest_start_date",contest.getStartingTime());
                intent.putExtra("contest_end_date",contest.getEndTime());
                intent.putExtra("contest_img",contest.getImgResId());
                intent.putExtra("contest_url",contest.getContestLink());

                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(contest.getContestLink()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContests.size();
    }

    public static class DsAlgoViewHolder extends RecyclerView.ViewHolder {

        ImageView siteImage;
        TextView name;
        TextView startTime;

        View rootView;

        public DsAlgoViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            siteImage = itemView.findViewById(R.id.site_image);
            name = itemView.findViewById(R.id.contest_name);
            startTime = itemView.findViewById(R.id.start_time);
        }
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}