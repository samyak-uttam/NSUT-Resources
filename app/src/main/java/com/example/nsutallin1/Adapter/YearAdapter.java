package com.example.nsutallin1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsutallin1.R;

import java.util.ArrayList;

public class YearAdapter extends RecyclerView.Adapter<YearAdapter.YearViewHolder> {


    public interface ListItemClickListener {
        void onYearItemClick(int clickedItemIndex);
    }

    private int mNumberItems;
    private ListItemClickListener mOnClickListener;

    private static ArrayList<String> yearNames=new ArrayList<>();
    private static ArrayList<Integer> yearImages=new ArrayList<>();


    public YearAdapter(int NumberItems,ListItemClickListener clickListener,ArrayList<String> yearNames1,ArrayList<Integer> yearImages1)
    {
        mNumberItems=NumberItems;
        mOnClickListener=clickListener;
        yearNames=yearNames1;
        yearImages=yearImages1;
    }

    @NonNull
    @Override
    public YearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        Context context=viewGroup.getContext();
        int layoutIdForListItem= R.layout.year_view_holder;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately=false;

        View view=inflater.inflate(layoutIdForListItem,viewGroup,shouldAttachToParentImmediately);
        YearAdapter.YearViewHolder viewHolder=new YearAdapter.YearViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YearViewHolder holder, int position) {
      holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class YearViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView yearName;
        ImageView yearImage;

        public YearViewHolder(@NonNull View itemView) {
            super(itemView);

            yearImage=(ImageView) itemView.findViewById(R.id.year_image);
            yearName=(TextView) itemView.findViewById(R.id.year_name);

            itemView.setOnClickListener(this);
        }

        public void bind(int position) {

            yearName.setText(yearNames.get(position));
            yearImage.setImageResource(yearImages.get(position));
            /*if (position==0)
            {
                yearName.setText(Integer.toString(1));
                yearImage.setImageResource(R.drawable.year_placeholder);
            }

            else if (position==1)
            {
                yearName.setText(Integer.toString(2));
                yearImage.setImageResource(R.drawable.year_placeholder);
            }

            else if (position==2)
            {
                yearName.setText(Integer.toString(3));
                yearImage.setImageResource(R.drawable.year_placeholder);
            }

            else if (position==3)
            {
                yearName.setText(Integer.toString(4));
                yearImage.setImageResource(R.drawable.year_placeholder);
            }*/
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onYearItemClick(clickedPosition);
        }
    }
}
