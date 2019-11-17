package com.example.nsutallin1.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.example.nsutallin1.Activity.PdfActivity;
import com.example.nsutallin1.Adapter.SavedAdapter;
import com.example.nsutallin1.Class.SavedData;
import com.example.nsutallin1.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class SavedFragment extends Fragment implements SavedAdapter.ListItemClickListener, PopupMenu.OnMenuItemClickListener {

    private ArrayList<SavedData> mSavedData, copy;
    private RecyclerView mRecyclerView;
    private SavedAdapter mAdapter;
    private LinearLayout emptyLayout;
    private CardView clearResults;

    private int savedItemToBeDeletedId;

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
        emptyLayout = rootView.findViewById(R.id.empty_layout);

        getFiles();

        mRecyclerView = rootView.findViewById(R.id.saved_rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new SavedAdapter(mSavedData, this);
        mRecyclerView.setAdapter(mAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        mAdapter.notifyDataSetChanged();
    }

    private void getFiles() {
        mSavedData.clear();
        emptyLayout.setVisibility(View.VISIBLE);
        File listFile[] = getActivity().getExternalFilesDir(null).listFiles();
        if (listFile != null && listFile.length > 0) {
            emptyLayout.setVisibility(View.GONE);
            for (int i = 0; i < listFile.length; i++) {
                String dataName = listFile[i].getName().split("&")[0];
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
    }

    @Override
    public void onResume() {
        super.onResume();
        getFiles();
        mAdapter.notifyDataSetChanged();
    }

    public void onSavedMenuClicked(int position, View view) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        savedItemToBeDeletedId = position;
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.saved_menu, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(this);
    }

    @Override
    public void OnSavedItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), PdfActivity.class);
        intent.putExtra("name", mSavedData.get(clickedItemIndex).getDataName());
        getActivity().startActivity(intent);
    }


    private void deleteItem() {
        SavedData dataToBeDeleted = mSavedData.get(savedItemToBeDeletedId);
        Log.e("saved", String.valueOf(savedItemToBeDeletedId));
        String dataType = dataToBeDeleted.getDataType();
        String dataName = dataToBeDeleted.getDataName();

        String itemToBeDeleted = dataName + "&" + dataType + ".encrypted";

        File listFile[] = getActivity().getExternalFilesDir(null).listFiles();
        Log.e("Size:", String.valueOf(listFile.length));
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].getName().equals(itemToBeDeleted)) {

                    mSavedData.remove(savedItemToBeDeletedId);
                    listFile[i].delete();
                    mAdapter.notifyDataSetChanged();

                    if (mSavedData.size() == 0) {
                        emptyLayout.setVisibility(View.VISIBLE);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.delete:
                deleteItem();
                break;
        }
        return true;
    }
}
