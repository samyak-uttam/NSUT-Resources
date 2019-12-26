package com.educational.nsutresources.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.LoaderManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.educational.nsutresources.Adapter.NoticeAdapter;
import com.educational.nsutresources.Class.Notice;
import com.educational.nsutresources.Loader.NoticeLoader;
import com.educational.nsutresources.R;

import java.util.ArrayList;

public class NoticesActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<Notice>> {

    private RecyclerView mRecyclerView;
    private NoticeAdapter mNoticeAdapter;
    private ArrayList<Notice> notices;


    private static int REQUEST_PERMISSION = 1;
    private static final int NOTICE_LOADER_ID = 1;

    private TextView mEmptyStateTextView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        notices = new ArrayList<>();

        mRecyclerView = findViewById(R.id.notices_rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEmptyStateTextView = findViewById(R.id.empty_list_view);

        mNoticeAdapter = new NoticeAdapter(notices, this);
        mRecyclerView.setAdapter(mNoticeAdapter);

        swipeRefreshLayout=findViewById(R.id.swiperefresh);


        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        myUpdateOperation();
                    }
                }
        );

        mRecyclerView.setVisibility(View.GONE);
        mEmptyStateTextView.setVisibility(View.VISIBLE);

        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

           myUpdateOperation();
        } else {

            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    private void myUpdateOperation() {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(NOTICE_LOADER_ID, null, this);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @NonNull
    @Override
    public android.content.Loader<ArrayList<Notice>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NoticeLoader(this);
    }



    @Override
    public void onLoadFinished(android.content.Loader<ArrayList<Notice>> loader, ArrayList<Notice> data) {
        notices.clear();
        if (data != null && !data.isEmpty()) {
            notices.addAll(data);
            mNoticeAdapter.notifyDataSetChanged();
            mEmptyStateTextView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mEmptyStateTextView.setText(R.string.no_notices);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }

        View loadingIndicator = findViewById(R.id.loading_spinner);
        loadingIndicator.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(android.content.Loader<ArrayList<Notice>> loader) {
        notices.clear();
        mNoticeAdapter.notifyDataSetChanged();
    }
}