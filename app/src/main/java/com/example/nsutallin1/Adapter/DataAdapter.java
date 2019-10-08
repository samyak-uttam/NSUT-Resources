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

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    public interface ListItemClickListener
    {
        void onDataItemClick(int clickedItemIndex);
    }

    private int mNumberItems;
    private ListItemClickListener mOnClickListener;

    private static ArrayList<String> dataNames=new ArrayList<>();
    private static ArrayList<Integer> dataImages=new ArrayList<>();

    public DataAdapter(int NumItems,ListItemClickListener clickListener,ArrayList<String> dataNames1,ArrayList<Integer> dataImages1)
    {
        mNumberItems=NumItems;
        mOnClickListener=clickListener;
        dataNames=dataNames1;
        dataImages=dataImages1;
    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        Context context=viewGroup.getContext();
        int layoutIdForListItem= R.layout.data_view_holder;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately=false;

        View view=inflater.inflate(layoutIdForListItem,viewGroup,shouldAttachToParentImmediately);
        DataAdapter.DataViewHolder viewHolder=new DataAdapter.DataViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
      holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dataName;
        ImageView dataImage;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            dataImage=(ImageView) itemView.findViewById(R.id.data_image);
            dataName=(TextView) itemView.findViewById(R.id.data_name);

            itemView.setOnClickListener(this);
        }

        public void bind(int position) {

            dataName.setText(dataNames.get(position));
            dataImage.setImageResource(dataImages.get(position));

           /* if (position==0)
            {
                dataName.setText("Books");
                dataImage.setImageResource(R.drawable.notes_black);
            }

            else if (position==1)
            {
                dataImage.setImageResource(R.drawable.pages_black);
                dataName.setText("Papers");
            }

            else if (position==2)
            {
                dataImage.setImageResource(R.drawable.notes_black);
                dataName.setText("Notes");
            }

            else if (position==3)
            {
                dataImage.setImageResource(R.drawable.pages_black);
                dataName.setText("Practicals");
            }*/

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onDataItemClick(clickedPosition);
        }
    }
}
