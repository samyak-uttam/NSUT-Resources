package com.example.nsutallin1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.Activity.DataResultActivity;
import com.example.nsutallin1.Class.Subject;
import com.example.nsutallin1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private ArrayList<Subject> mSubjects;
    private int imageCode;
    private Context mContext;
    private Map<String, String> map;
    private String[] data;

    public SubjectAdapter(ArrayList<Subject> mSubjects, int imageCode, String[] data, Context mContext) {
        this.mSubjects = mSubjects;
        this.imageCode = imageCode;
        this.data = data;
        this.mContext = mContext;
        map = new HashMap<>();
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_item, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        final Subject subject = mSubjects.get(position);

        switch (imageCode) {
            case 0:
                holder.branchImage.setImageResource(R.drawable.one);
                break;
            case 1:
                holder.branchImage.setImageResource(R.drawable.maths);
                break;
            case 2:
                holder.branchImage.setImageResource(R.drawable.coe);
                break;
            case 3:
                holder.branchImage.setImageResource(R.drawable.it);
                break;
            case 4:
                holder.branchImage.setImageResource(R.drawable.ece);
                break;
            case 5:
                holder.branchImage.setImageResource(R.drawable.ice);
                break;
            case 6:
                holder.branchImage.setImageResource(R.drawable.me);
                break;
            case 7:
                holder.branchImage.setImageResource(R.drawable.mpae);
                break;
            case 8:
                holder.branchImage.setImageResource(R.drawable.bt);
                break;
        }

        holder.subjectName.setText(subject.getSubjectName());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DataResultActivity.class);
                intent.putExtra("branchName", data[0]);
                intent.putExtra("dataType", data[1]);
                intent.putExtra("subName", subject.getSubjectName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        TextView subjectName;
        ImageView branchImage;

        View rootView;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            subjectName = itemView.findViewById(R.id.subject_name);
            branchImage = itemView.findViewById(R.id.branch_image);
        }
    }
}
