package com.educational.nsutresources.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
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

import com.educational.nsutresources.Adapter.SavedAdapter;
import com.educational.nsutresources.Class.SavedData;
import com.educational.nsutresources.Data.BookDbHelper;
import com.educational.nsutresources.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import com.educational.nsutresources.Data.ContractClass.BookEntry;

public class SavedFragment extends Fragment implements SavedAdapter.ListItemClickListener, DialogFragment.NoticeDialogListener {

    private ArrayList<SavedData> mSavedData, copy;
    private RecyclerView mRecyclerView;
    private SavedAdapter mAdapter;
    private LinearLayout emptyLayout, searchLayout;
    private TextView emptyTV;
    private CardView clearResults;

    private BookDbHelper mDbHelper;

    private int savedItemToBeDeletedId;
    private final int confirmation = 1;

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

        mDbHelper = new BookDbHelper(getContext());
        loadBooks();

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

        if (mSavedData.size() == 0) {
            emptyLayout.setVisibility(View.VISIBLE);
            emptyTV.setText("No Files Found.");
        }
        mAdapter.notifyDataSetChanged();
    }

    private void loadBooks() {

        mSavedData.clear();

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_BOOK_NAME,
                BookEntry.COLUMN_BOOK_DATA};

        Cursor cursor = database.query(BookEntry.TABLE_NAME, projection, null,
                null, null, null, null);

        try {
            if (cursor.getCount() == 0) {
                emptyTV.setText("No Files Saved.");
                emptyLayout.setVisibility(View.VISIBLE);
            } else {
                emptyLayout.setVisibility(View.GONE);

                int bookNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME);
                int bookDataColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_DATA);

                while (cursor.moveToNext()) {
                    String currentBookName = cursor.getString(bookNameColumnIndex);
                    String currentBookData = cursor.getString(bookDataColumnIndex);

                    mSavedData.add(new SavedData(currentBookName, currentBookData));
                }

                Collections.sort(mSavedData, new Comparator<SavedData>() {
                    @Override
                    public int compare(SavedData o1, SavedData o2) {
                        return o1.getDataType().compareTo(o2.getDataType());
                    }
                });
                copy = new ArrayList<>(mSavedData);
            }
        } finally {
            // finally close the cursor
            cursor.close();
        }
        if (mSavedData.isEmpty()) {
            searchLayout.setVisibility(View.GONE);
        } else {
            searchLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadBooks();
        mAdapter.notifyDataSetChanged();
    }

    public void onSavedMenuClicked(int position, View view) {
        savedItemToBeDeletedId = position;
        showdialog();
    }

    @Override
    public void OnSavedItemClick(int clickedItemIndex) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        File[] listFile = getActivity().getExternalFilesDir(null).listFiles();
        int i = 0;
        for (i = 0; i < listFile.length; i++) {
            if (listFile[i].getName().equals(mSavedData.get(clickedItemIndex).getDataName() + ".pdf")) {
                break;
            }
        }
        intent.setDataAndType(FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext()
                .getPackageName() + ".provider", listFile[i]), "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }


    private void deleteItem() {

        SavedData dataToBeDeleted = mSavedData.get(savedItemToBeDeletedId);
        String dataName = dataToBeDeleted.getDataName();

        String itemToBeDeleted = dataName + ".pdf";

        deleteFromDatabase(dataName);

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

    private void deleteFromDatabase(String bookName) {
        SQLiteDatabase readableDatabase = mDbHelper.getReadableDatabase();

        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_BOOK_NAME};

        Cursor cursor = readableDatabase.query(BookEntry.TABLE_NAME, projection, null,
                null, null, null, null);

        try {
            if (cursor.getCount() > 0) {

                int bookIdIndex = cursor.getColumnIndex(BookEntry._ID);
                int bookNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME);

                while (cursor.moveToNext()) {
                    if (cursor.getString(bookNameColumnIndex).equals(bookName)) {
                        SQLiteDatabase writableDatabase = mDbHelper.getWritableDatabase();
                        writableDatabase.delete(BookEntry.TABLE_NAME, BookEntry._ID
                                + "=" + cursor.getInt(bookIdIndex), null);
                        break;
                    }
                }
            }
        } finally {
            // finally close the cursor
            cursor.close();
        }
    }

    private void showdialog() {
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getActivity().getSupportFragmentManager(), "Dialog Fragment");
        dialogFragment.setTargetFragment(SavedFragment.this, confirmation);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.e("SavedFragment", "getting PositiveConfirmation");
        deleteItem();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Log.e("SavedFragment", "getting NegtiveConfirmation");
    }

}
