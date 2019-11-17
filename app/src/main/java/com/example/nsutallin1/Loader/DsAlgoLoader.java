package com.example.nsutallin1.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;

import com.example.nsutallin1.Class.Contest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DsAlgoLoader extends AsyncTaskLoader<ArrayList<Contest>> {


    private Document doc = null;
    private ArrayList<Contest> contests;

    public DsAlgoLoader(@NonNull Context context) {
        super(context);
        contests = new ArrayList<>();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<Contest> loadInBackground() {

        try {
            doc = Jsoup.connect("https://www.codechef.com/contests").get();
            Element table = doc.select("tbody").get(1);

            Elements rows = table.select("tr");

            for (int i = 1; i < rows.size(); i++) {
                if (!rows.get(i).select("tr").isEmpty()) {
                    Element row = rows.get(i);
                    String name, startingTime, endTime, contestLink;
                    if(row.select("td").hasText()) {
                        name = row.select("td").get(1).text();
                        startingTime = row.select("td").get(2).text();
                        endTime = row.select("td").get(3).text();

                        contestLink = "https://www.codechef.com/" + row.select("a").attr("href");
                        contests.add(new Contest(name, startingTime, endTime, contestLink));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contests;
    }
}
