package com.example.nsutallin1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.nsutallin1.Adapter.NoticeAdapter;
import com.example.nsutallin1.Class.Notice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NoticesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private NoticeAdapter mNoticeAdapter;
    private static ArrayList<Notice> notices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        notices = new ArrayList<Notice>();

        new GetNotices().execute();
        Log.v("NoticesActivity", "Size of the notices is " + notices.size());
    }

    private class GetNotices extends AsyncTask<Void, Void, Void> {
        Document doc = null;


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                doc = Jsoup.connect("https://imsnsit.org/imsnsit/notifications.php").get();
                Element table = doc.select("tbody").get(1);

                Elements rows = table.select("tr");

                for (int i = 3; i < rows.size(); i++) {
                    if (!rows.get(i).select("tr").isEmpty()) {
                        Element row = rows.get(i);
                        String date, title, publishedby;
                        if(row.select("td").hasText()) {
                            date = row.select("td").get(0).text();
                            String temp = row.select("td").get(1).text();
                            title = temp.split("Published By:") [0];
                            publishedby = temp.split("Published By:") [1];

                            notices.add(new Notice(date, title, publishedby));
                        }
                    }
                }
                Log.v("NoticesActivity", "Size of the notices is " + notices.size());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mRecyclerView = findViewById(R.id.notices_rec_view);
            mNoticeAdapter = new NoticeAdapter(notices, NoticesActivity.this);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(NoticesActivity.this));
            mRecyclerView.setAdapter(mNoticeAdapter);
        }
    }
}
