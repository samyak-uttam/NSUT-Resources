package com.educational.nsutresources.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import android.content.AsyncTaskLoader;

import com.educational.nsutresources.Class.Notice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NoticeLoader extends AsyncTaskLoader<ArrayList<Notice>> {

    private Document doc = null;
    private ArrayList<Notice> notices;

    public NoticeLoader(@NonNull Context context) {
        super(context);
        notices = new ArrayList<>();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Notice> loadInBackground() {
        try {
            doc = Jsoup.connect("https://imsnsit.org/imsnsit/notifications.php").get();
            Element table = doc.select("tbody").get(1);

            Elements rows = table.select("tr");

            for (int i = 3; i < rows.size(); i++) {
                if (!rows.get(i).select("tr").isEmpty()) {
                    Element row = rows.get(i);
                    String date, title, publishedby, downloadLink;
                    if(row.select("td").hasText()) {
                        date = row.select("td").get(0).text();
                        String temp = row.select("td").get(1).text();
                        title = temp.split("Published By:") [0];
                        publishedby = temp.split("Published By:") [1];

                        downloadLink = row.select("a").attr("href");
                        notices.add(new Notice(date, title, publishedby, downloadLink));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return notices;
    }
}
