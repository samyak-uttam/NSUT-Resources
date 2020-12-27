package com.educational.nsutresources.Loader;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.loader.content.AsyncTaskLoader;

import com.educational.nsutresources.Notifications.AlertReceiver;
import com.educational.nsutresources.Class.Contest;
import com.educational.nsutresources.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DsAlgoLoader extends AsyncTaskLoader<ArrayList<Contest>> {

    private Document doc = null;
    private Document doc1 = null;
    int tableNo;
    private ArrayList<Contest> contests;
    private SimpleDateFormat ccsdf, cfsdf;
    private Context mContext;

    private int request_code;

    public DsAlgoLoader(@NonNull Context context, int tableNo) {
        super(context);
        request_code = 0;
        mContext = context;
        contests = new ArrayList<>();
        this.tableNo = tableNo;
        ccsdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
        cfsdf = new SimpleDateFormat("MMM/dd/yyyy HH:mm", Locale.ENGLISH);
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

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(ccsdf.parse(startingTime));
                        if (tableNo == 2 && (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 60000 > 20)
                            setNotification(cal, "Codechef", name);

                        contestLink = "https://www.codechef.com/" + row.select("a").attr("href");
                        contests.add(new Contest(R.drawable.codechef, name, startingTime, endTime, contestLink));
                    }
                }
            }

        } catch (IOException | ParseException e) {
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
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }

                        contestLink = row.select("a").attr("href");

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(cfsdf.parse(startingTime));
                        cal.add(Calendar.HOUR, 3);
                        cal.add(Calendar.MINUTE, -30);
                        if (tableNo == 2 && (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 60000 > 20)
                            setNotification(cal, "Codeforces", name);

                        final Date current = Calendar.getInstance().getTime();
                        final Date stTime = cal.getTime();
                        final Date endTime = new Date(stTime.getTime() + durMills);

                        if (stTime.compareTo(current) <= 0 && tableNo == 1) {
                            contests.add(new Contest(R.drawable.codeforces, name, stTime.toString(), endTime.toString(), contestLink));
                        } else if (stTime.compareTo(current) > 0 && tableNo == 2) {
                            contests.add(new Contest(R.drawable.codeforces, name, stTime.toString(), endTime.toString(), contestLink));
                        }
                    }
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void setNotification(Calendar cal, String site, String contest) {
        cal.add(Calendar.MINUTE, -20);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mContext, AlertReceiver.class);
        intent.putExtra("title", site + " Contest.");
        intent.putExtra("description", contest + " will start in 20 mins.");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, request_code, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        request_code++;
    }
}
