package com.example.nsutallin1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.Class.Subject;
import com.example.nsutallin1.R;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private ArrayList<Subject> mSubjects;
    private int imageCode;
    private Context mContext;

    public SubjectAdapter(ArrayList<Subject> mSubjects, int imageCode, Context mContext) {
        this.mSubjects = mSubjects;
        this.imageCode = imageCode;
        this.mContext = mContext;
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
            case 1:
                holder.branchImage.setImageResource(R.drawable.coe);
                break;
            case 2:
                holder.branchImage.setImageResource(R.drawable.it);
                break;
            case 3:
                holder.branchImage.setImageResource(R.drawable.ece);
                break;
            case 4:
                holder.branchImage.setImageResource(R.drawable.ice);
                break;
            case 5:
                holder.branchImage.setImageResource(R.drawable.me);
                break;
            case 6:
                holder.branchImage.setImageResource(R.drawable.mpae);
                break;
            case 7:
                holder.branchImage.setImageResource(R.drawable.bt);
                break;
        }

        holder.subjectName.setText(subject.getSubjectName());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, subject.getSubjectName() + " clicked", Toast.LENGTH_SHORT).show();
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
