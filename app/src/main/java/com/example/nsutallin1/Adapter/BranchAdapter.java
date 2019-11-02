package com.example.nsutallin1.Adapter;

import android.util.SparseBooleanArray;
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
import java.util.List;

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.BranchViewHolder> {

    public interface ListItemClickListener {
        void onBranchItemClick(int clickedItemIndex);
    }

    final private ListItemClickListener mOnClickListener;

    private ArrayList<Data> branches;
    private SparseBooleanArray selectedItems;

    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items =
                new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }




    public BranchAdapter( ArrayList<Data> branches,ListItemClickListener listener) {
        mOnClickListener = listener;
        this.branches = branches;
    }


    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_viewholder, parent, false);
        return new BranchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder holder, int position) {

        final Data data = branches.get(position);

        holder.brachName.setText(data.getName());
        holder.branchImage.setImageResource(data.getImageResID());


    }

    @Override
    public int getItemCount() {
        return branches.size();
    }

    public class BranchViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView brachName;
        ImageView branchImage;
        public BranchViewHolder(@NonNull View itemView) {
            super(itemView);

            brachName = itemView.findViewById(R.id.branch_name);
            branchImage = itemView.findViewById(R.id.branch_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onBranchItemClick(clickedPosition);
        }
    }
}
