package com.example.nsutallin1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.Class.Data;
import com.example.nsutallin1.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    public interface ListItemClickListener
    {
        void onDataItemClick(int clickedItemIndex);
    }

    private ListItemClickListener mOnClickListener;

    private ArrayList<Data> data;

    public DataAdapter(ListItemClickListener clickListener, ArrayList<Data> data) {
        mOnClickListener=clickListener;
        this.data = data;
    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_view_holder, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        final Data curData = data.get(position);

        holder.dataName.setText(curData.getName());
        holder.dataImage.setImageResource(curData.getImageResID());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dataName;
        ImageView dataImage;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            dataImage = itemView.findViewById(R.id.tempImg);
            dataName = itemView.findViewById(R.id.tempText);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onDataItemClick(clickedPosition);
        }
    }
}
