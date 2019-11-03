package com.example.nsutallin1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    public interface ListItemClickListener {
        void OnDataItemClick(int clickedItemIndex);
    }

    private ArrayList<String> mData;
    final private ListItemClickListener mOnClickListener;

    public DataAdapter(ArrayList<String> mData, ListItemClickListener listener) {
        this.mData = mData;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        final String string = mData.get(position);

        holder.dataImage.setImageResource(R.drawable.file_download);
        holder.dataName.setText(string);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView dataName;
        ImageView dataImage;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            dataName = itemView.findViewById(R.id.data_name);
            dataImage = itemView.findViewById(R.id.data_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.OnDataItemClick(clickedPosition);
        }
    }
}
