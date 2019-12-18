package com.ourcoolapp.nsutresources.Adapter;

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

import com.ourcoolapp.nsutresources.Class.Syllabus;
import com.ourcoolapp.nsutresources.R;

import java.util.ArrayList;

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.SyllabusViewHolder> {

    private ArrayList<Syllabus> msyllabus;
    private Context mcontext;

    public SyllabusAdapter(ArrayList<Syllabus> msyllabus, Context mcontext) {
        this.msyllabus = msyllabus;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public SyllabusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.syllabus_item, parent, false);
        return new SyllabusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SyllabusViewHolder holder, int position) {

        final Syllabus syllabus = msyllabus.get(position);

        holder.branchName.setText(syllabus.getBranch());
        holder.branchImage.setImageResource(syllabus.getImageResID());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://drive.google.com/file/d/" + syllabus.getFileID()));
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return msyllabus.size();
    }

    public static class SyllabusViewHolder extends RecyclerView.ViewHolder {

        ImageView branchImage;
        TextView branchName;

        View rootView;

        public SyllabusViewHolder(@NonNull View itemView) {
            super(itemView);

            rootView = itemView;
            branchImage = itemView.findViewById(R.id.branch_image);
            branchName = itemView.findViewById(R.id.branch_name);
        }
    }
}
