package com.example.nsutallin1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.Class.Subject;
import com.example.nsutallin1.R;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private ArrayList<Subject> mSubjects;

    public SubjectAdapter(ArrayList<Subject> mSubjects, Context context) {
        this.mSubjects = mSubjects;
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

        holder.branchImage.setImageResource(R.drawable.ece);
        holder.subjectName.setText(subject.getSubjectName());
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
