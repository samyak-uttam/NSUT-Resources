package com.ourcoolapp.nsutresources.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.loader.content.AsyncTaskLoader;

import com.ourcoolapp.nsutresources.Class.Contest;
import com.ourcoolapp.nsutresources.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DsAlgoLoader extends AsyncTaskLoader<ArrayList<Contest>> {


    private Document doc = null;
    private Document doc1 = null;
    int tableNo;
    private ArrayList<Contest> contests;

    public DsAlgoLoader(@NonNull Context context, int tableNo) {
        super(context);
        contests = new ArrayList<>();
        this.tableNo = tableNo;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<Contest> loadInBackground() {

        try {
            contests.clear();
            doc = Jsoup.connect("https://www.codechef.com/contests").get();
            Element table = doc.select("tbody").get(tableNo);

            Elements rows = table.select("tr");

            for (int i = 0; i < rows.size(); i++) {
                if (!rows.get(i).select("tr").isEmpty()) {
                    Element row = rows.get(i);
                    String name, startingTime, endTime, contestLink;
                    if (row.select("td").hasText()) {
                        name = row.select("td").get(1).text();
                        startingTime = row.select("td").get(2).text();
                        endTime = row.select("td").get(3).text();

                        contestLink = "https://www.codechef.com/" + row.select("a").attr("href");
                        contests.add(new Contest(R.drawable.codechef, name, startingTime, endTime, contestLink));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        codeForcesContests();

        return contests;
    }

    private void codeForcesContests() {

        try {
            doc1 = Jsoup.connect("https://codeforces.com/contests").get();
            Element table = doc1.select("tbody").get(0);

            Elements rows = table.select("tr");

            for (int i = 0; i < rows.size(); i++) {
                if (!rows.get(i).select("tr").isEmpty()) {
                    Element row = rows.get(i);
                    String name, startingTime, durStr, contestLink;
                    if (row.select("td").hasText()) {
                        name = row.select("td").get(0).text();
                        startingTime = row.select("td").get(2).text();
                        durStr = row.select("td").get(3).text().split(":")[0];

                        int durMills = 0;
                        try {
                            durMills = Integer.parseInt(durStr) * 60 * 60 * 1000;
                        } catch(NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }

                        contestLink = row.select("a").attr("href");

                        final Date current = Calendar.getInstance().getTime();
                        final Date stTime = new Date(startingTime);
                        final Date endTime = new Date(stTime.getTime() + durMills);

                        if (stTime.compareTo(current) <= 0 && tableNo == 1) {
                            contests.add(new Contest(R.drawable.codeforces, name, startingTime, endTime.toString(), contestLink));
                        } else if(stTime.compareTo(current) > 0 && tableNo == 2){
                            contests.add(new Contest(R.drawable.codeforces, name, startingTime, endTime.toString(), contestLink));
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
