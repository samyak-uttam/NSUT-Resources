package com.ourcoolapp.nsutresources.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ourcoolapp.nsutresources.Class.SavedData;
import com.ourcoolapp.nsutresources.R;

import java.util.ArrayList;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.SavedViewHolder> {

    public interface ListItemClickListener {
        void OnSavedItemClick(int clickedItemIndex);

        void onSavedMenuClicked(int clickedItemIndex, View view);
    }

    private ArrayList<SavedData> mSavedData;
    private Context mContext;
    final private ListItemClickListener mOnClickListener;

    public SavedAdapter(ArrayList<SavedData> mSavedData, ListItemClickListener listener) {
        this.mSavedData = mSavedData;
        mOnClickListener = listener;
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
        if (type.equals("books")) {
            holder.dataImage.setImageResource(R.drawable.book);
        } else if (type.equals("notes")) {
            holder.dataImage.setImageResource(R.drawable.notes);
        } else if (type.equals("papers")) {
            holder.dataImage.setImageResource(R.drawable.papers);
        } else {
            holder.dataImage.setImageResource(R.drawable.practicals);
        }

        holder.dataName.setText(name);
    }

    @Override
    public int getItemCount() {
        return mSavedData.size();
    }

    public class SavedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView dataName;
        ImageView dataImage;
        ImageView savedMenuIcon;

        public SavedViewHolder(@NonNull View itemView) {
            super(itemView);
            dataName = itemView.findViewById(R.id.data_name);
            dataImage = itemView.findViewById(R.id.data_image);
            savedMenuIcon = itemView.findViewById(R.id.saved_menu);

            itemView.setOnClickListener(this);

            savedMenuIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        int position = getAdapterPosition();
                        mOnClickListener.onSavedMenuClicked(position, v);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.OnSavedItemClick(clickedPosition);
        }
    }
}
