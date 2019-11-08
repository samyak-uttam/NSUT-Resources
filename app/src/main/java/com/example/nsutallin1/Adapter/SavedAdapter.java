package com.example.nsutallin1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.Activity.PdfActivity;
import com.example.nsutallin1.Class.SavedData;
import com.example.nsutallin1.R;

import java.util.ArrayList;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.SavedViewHolder> {

    private ArrayList<SavedData> mSavedData;
    private Context mContext;

    public SavedAdapter(ArrayList<SavedData> mSavedData, Context mContext) {
        this.mSavedData = mSavedData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SavedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_item, parent, false);
        return new SavedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedViewHolder holder, final int position) {
        String name = mSavedData.get(position).getDataName();
        String type = mSavedData.get(position).getDataType();
        if(type.equals("books")) {
            holder.dataImage.setImageResource(R.drawable.book);
        } else if(type.equals("notes")) {
            holder.dataImage.setImageResource(R.drawable.notes);
        } else if(type.equals("papers")) {
            holder.dataImage.setImageResource(R.drawable.papers);
        } else {
            holder.dataImage.setImageResource(R.drawable.practicals);
        }

        holder.dataName.setText(name);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PdfActivity.class);
                intent.putExtra("index", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSavedData.size();
    }

    public static class SavedViewHolder extends RecyclerView.ViewHolder {

        TextView dataName;
        ImageView dataImage;
        View rootView;

        public SavedViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            dataName = itemView.findViewById(R.id.data_name);
            dataImage = itemView.findViewById(R.id.data_image);
        }
    }
}
