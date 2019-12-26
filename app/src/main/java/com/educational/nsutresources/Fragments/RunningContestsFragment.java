package com.educational.nsutresources.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.loader.app.LoaderManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.educational.nsutresources.Adapter.DsAlgoAdapter;
import com.educational.nsutresources.Class.Contest;
import com.educational.nsutresources.Loader.DsAlgoLoader;
import com.educational.nsutresources.R;

import java.util.ArrayList;


public class RunningContestsFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Contest>> {

    private RecyclerView mRecyclerView;
    private ArrayList<Contest> mContests;
    private DsAlgoAdapter mAdapter;
    private ImageView emptyIV;
    private TextView emptyTV;
    private View rootView;

    public RunningContestsFragment() {

    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_contest_common, container, false);

        mContests = new ArrayList<>();
        emptyIV = rootView.findViewById(R.id.empty_image_view);
        emptyTV = rootView.findViewById(R.id.empty_text_view);

        mRecyclerView = rootView.findViewById(R.id.rv_contest);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new DsAlgoAdapter(mContests, getActivity());
        mRecyclerView.setAdapter(mAdapter);


        ConnectivityManager connMgr =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1, null, this);
        } else {

            emptyIV.setImageResource(R.drawable.no_internet);
            emptyTV.setText(R.string.no_internet_connection);
            View loadingIndicator = rootView.findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);
        }

        return rootView;
    }

    @NonNull
    @Override
    public Loader<ArrayList<Contest>> onCreateLoader(int id, @Nullable Bundle args) {
        return new DsAlgoLoader(getActivity(), 1);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Contest>> loader, ArrayList<Contest> data) {
        mContests.clear();
        if (data != null && !data.isEmpty()) {
            mContests.addAll(data);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyIV.setImageResource(R.drawable.empty);
            emptyTV.setText("No Contests found.");
        }

        View loadingIndicator = rootView.findViewById(R.id.loading_spinner);
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Contest>> loader) {
        mContests.clear();
        mAdapter.notifyDataSetChanged();
    }
}
