package com.example.nsutallin1.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.nsutallin1.Class.DsAlgoData;

import java.util.ArrayList;

public class DsAlgoLoader extends AsyncTaskLoader<ArrayList<DsAlgoData>> {

    public DsAlgoLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<DsAlgoData> loadInBackground() {
        return null;
    }
}
