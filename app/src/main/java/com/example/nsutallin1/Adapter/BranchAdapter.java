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

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.BranchViewHolder> {

    public interface ListItemClickListener {
        void onBranchItemClick(int clickedItemIndex);
    }

    private int mNumberItems;
    final private ListItemClickListener mOnClickListener;

    private static ArrayList<String> branchNames=new ArrayList<>();
    private static ArrayList<Integer> branchImages=new ArrayList<>();

    public BranchAdapter(int NumberItems,ListItemClickListener listener,ArrayList<String> branchNames1,ArrayList<Integer> branchImages1)
    {
        mNumberItems=NumberItems;
        mOnClickListener=listener;
        branchNames=branchNames1;
        branchImages=branchImages1;
    }


    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context=viewGroup.getContext();
        int layoutIdForListItem=R.layout.branch_view_holder;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately=false;

        View view=inflater.inflate(layoutIdForListItem,viewGroup,shouldAttachToParentImmediately);
        BranchViewHolder viewHolder=new BranchViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class BranchViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView brachName;
        ImageView branchImage;
        public BranchViewHolder(@NonNull View itemView) {
            super(itemView);

            brachName=(TextView) itemView.findViewById(R.id.branch_name);
            branchImage=(ImageView) itemView.findViewById(R.id.branch_image);

            itemView.setOnClickListener(this);
        }

        public void bind(int position) {

            brachName.setText(branchNames.get(position));
            branchImage.setImageResource(branchImages.get(position));

            /*if (position==0) {
                branchImage.setImageResource(R.drawable.coe);
                brachName.setText("COE");
            }

            else if (position==1) {
                    branchImage.setImageResource(R.drawable.it);
                    brachName.setText("IT");
            }


            else if (position==2) {
                branchImage.setImageResource(R.drawable.ece);
                brachName.setText("ECE");
            }


            else if (position==3) {
                branchImage.setImageResource(R.drawable.ice);
                brachName.setText("ICE");
            }


            else if (position==4) {
                branchImage.setImageResource(R.drawable.mpae);
                brachName.setText("MPAE");
            }


            else if (position==5) {
                branchImage.setImageResource(R.drawable.me);
                brachName.setText("ME");
            }

            else if (position==6) {
                branchImage.setImageResource(R.drawable.bt);
                brachName.setText("BT");
            }*/
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onBranchItemClick(clickedPosition);
        }
    }
}
