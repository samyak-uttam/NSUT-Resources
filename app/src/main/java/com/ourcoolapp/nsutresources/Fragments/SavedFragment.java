package com.ourcoolapp.nsutresources.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ourcoolapp.nsutresources.Activity.PdfActivity;
import com.ourcoolapp.nsutresources.Adapter.SavedAdapter;
import com.ourcoolapp.nsutresources.Class.SavedData;
import com.ourcoolapp.nsutresources.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class SavedFragment extends Fragment implements SavedAdapter.ListItemClickListener,DialogFragment.NoticeDialogListener {

    private ArrayList<SavedData> mSavedData, copy;
    private RecyclerView mRecyclerView;
    private SavedAdapter mAdapter;
    private LinearLayout emptyLayout, searchLayout;
    private TextView emptyTV;
    private CardView clearResults;

    private int savedItemToBeDeletedId;
    private final int confirmation=1;

    public SavedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_saved, container, false);

        final EditText dataSearch = rootView.findViewById(R.id.data_search_et);
        ImageButton searchButton = rootView.findViewById(R.id.search_button);
        clearResults = rootView.findViewById(R.id.clear_results);
        clearResults.setVisibility(View.GONE);

        mSavedData = new ArrayList<>();
        searchLayout = rootView.findViewById(R.id.search_layout);
        emptyLayout = rootView.findViewById(R.id.empty_layout);
        emptyTV = rootView.findViewById(R.id.empty_text_view);

        getFiles();

        mRecyclerView = rootView.findViewById(R.id.saved_rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new SavedAdapter(mSavedData, this);
        mRecyclerView.setAdapter(mAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                emptyLayout.setVisibility(View.GONE);

                String search = dataSearch.getText().toString().trim();
                if (search.isEmpty()) {
                    dataSearch.setError("Please enter a valid data name.");
                    dataSearch.requestFocus();
                    return;
                }
                search(search.toLowerCase());
            }
        });

        clearResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyLayout.setVisibility(View.GONE);
                mSavedData.clear();
                mSavedData.addAll(copy);
                mAdapter.notifyDataSetChanged();
                clearResults.setVisibility(View.GONE);
                dataSearch.setText("");
            }
        });

        return rootView;
    }

    private void search(String search) {

        clearResults.setVisibility(View.VISIBLE);
        mSavedData.clear();
        mSavedData.addAll(copy);

        Iterator<SavedData> itr = mSavedData.iterator();
        while (itr.hasNext()) {
            if (!itr.next().getDataName().toLowerCase().startsWith(search)) {
                itr.remove();
            }
        }

        if(mSavedData.size() == 0) {
            emptyLayout.setVisibility(View.VISIBLE);
            emptyTV.setText("No Files Found.");
        }
        mAdapter.notifyDataSetChanged();
    }

    private void getFiles() {

        mSavedData.clear();
        emptyLayout.setVisibility(View.VISIBLE);
        emptyTV.setText("No Files Saved.");
        File[] listFile = getActivity().getExternalFilesDir(null).listFiles();
        if (listFile != null && listFile.length > 0) {
            emptyLayout.setVisibility(View.GONE);
            for (int i = 0; i < listFile.length; i++) {
                String dataName = listFile[i].getName().split("&")[0].substring(1);
                String dataType = listFile[i].getName().split("&")[1].split(".encrypted")[0];
                mSavedData.add(new SavedData(dataName, dataType));
            }

            Collections.sort(mSavedData, new Comparator<SavedData>() {
                @Override
                public int compare(SavedData o1, SavedData o2) {
                    return o1.getDataType().compareTo(o2.getDataType());
                }
            });
            copy = new ArrayList<>(mSavedData);
        }
        if(mSavedData.isEmpty()) {
            searchLayout.setVisibility(View.GONE);
        } else {
            searchLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getFiles();
        mAdapter.notifyDataSetChanged();
    }

    public void onSavedMenuClicked(int position, View view) {
        savedItemToBeDeletedId = position;
        showdialog();
    }

    @Override
    public void OnSavedItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), PdfActivity.class);
        intent.putExtra("name", mSavedData.get(clickedItemIndex).getDataName());
        getActivity().startActivity(intent);
    }


    private void deleteItem() {

        SavedData dataToBeDeleted = mSavedData.get(savedItemToBeDeletedId);
        String dataType = dataToBeDeleted.getDataType();
        String dataName = dataToBeDeleted.getDataName();

        String itemToBeDeleted = "." + dataName + "&" + dataType + ".encrypted";

        File listFile[] = getActivity().getExternalFilesDir(null).listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].getName().equals(itemToBeDeleted)) {

                    mSavedData.remove(savedItemToBeDeletedId);
                    copy.remove(savedItemToBeDeletedId);
                    listFile[i].delete();
                    mAdapter.notifyDataSetChanged();

                    if (mSavedData.size() == 0) {
                        emptyLayout.setVisibility(View.VISIBLE);
                    }
                    break;
                }
            }
        }

        if (mSavedData.isEmpty()) {
            searchLayout.setVisibility(View.GONE);
        }
    }

    private void showdialog() {
     DialogFragment dialogFragment=new DialogFragment();
     dialogFragment.show(getActivity().getSupportFragmentManager(),"Dialog Fragment");
     dialogFragment.setTargetFragment(SavedFragment.this,confirmation);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.e("SavedFragment","getting PositiveConfirmation");
        deleteItem();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Log.e("SavedFragment","getting NegtiveConfirmation");
    }

}
