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

public class YearAdapter extends RecyclerView.Adapter<YearAdapter.YearViewHolder> {


    public interface ListItemClickListener {
        void onYearItemClick(int clickedItemIndex);
    }

    private ListItemClickListener mOnClickListener;

    private ArrayList<Data> years;


    public YearAdapter(ListItemClickListener clickListener,ArrayList<Data> years) {
        mOnClickListener = clickListener;
        this.years = years;
    }

    @NonNull
    @Override
    public YearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.year_view_holder, parent, false);
        return new YearViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YearViewHolder holder, int position) {
        final Data year = years.get(position);

        holder.yearName.setText(year.getName());
        holder.yearImage.setImageResource(year.getImageResID());
    }

    @Override
    public int getItemCount() {
        return years.size();
    }

    public class YearViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView yearName;
        ImageView yearImage;

        public YearViewHolder(@NonNull View itemView) {
            super(itemView);

            yearImage = itemView.findViewById(R.id.year_image);
            yearName = itemView.findViewById(R.id.year_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onYearItemClick(clickedPosition);
        }
    }
}
