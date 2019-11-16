package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LoaderManager.LoaderCallbacks;
import android.app.LoaderManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nsutallin1.Adapter.DsAlgoAdapter;
import com.example.nsutallin1.Class.Contest;
import com.example.nsutallin1.Loader.DsAlgoLoader;
import com.example.nsutallin1.R;

import java.util.ArrayList;

public class DsAlgoActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<Contest>> {

    private RecyclerView mRecyclerView;
    private ArrayList<Contest> mContests;
    private DsAlgoAdapter mAdapter;
    private ImageView emptyIV;
    private TextView emptyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_algo);

        mContests = new ArrayList<>();
        emptyIV = findViewById(R.id.empty_image_view);
        emptyTV = findViewById(R.id.empty_text_view);

        mRecyclerView = findViewById(R.id.ds_algo_rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new DsAlgoAdapter(mContests, this);
        mRecyclerView.setAdapter(mAdapter);

        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1, null, this);
        } else {

            emptyIV.setImageResource(R.drawable.no_internet);
            emptyTV.setText(R.string.no_internet_connection);
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);
        }
    }


    @Override
    public android.content.Loader<ArrayList<Contest>> onCreateLoader(int id, Bundle args) {
        return new DsAlgoLoader(this);
    }

    @Override
    public void onLoadFinished(android.content.Loader<ArrayList<Contest>> loader, ArrayList<Contest> data) {
        mContests.clear();
        if (data != null && !data.isEmpty()) {
            mContests.addAll(data);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyIV.setImageResource(R.drawable.empty);
            emptyTV.setText("No Contests found.");
        }

        View loadingIndicator = findViewById(R.id.loading_spinner);
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(android.content.Loader<ArrayList<Contest>> loader) {
        mContests.clear();
        mAdapter.notifyDataSetChanged();
    }
}
