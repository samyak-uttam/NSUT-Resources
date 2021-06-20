package com.educational.nsutresources.Loader;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.loader.content.AsyncTaskLoader;

import com.educational.nsutresources.Notifications.AlertReceiver;
import com.educational.nsutresources.Class.Contest;
import com.educational.nsutresources.R;


import org.json.JSONException;
import org.json.JSONObject;
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
import java.util.HashMap;
import java.util.Locale;

public class DsAlgoLoader extends AsyncTaskLoader<ArrayList<Contest>> {

    private static final HashMap<String, Pair<String, Integer>> websites = new HashMap<>();
    private Document doc = null;
    private final Document doc1 = null;
    int tableNo;
    private final ArrayList<Contest> contests;
    private final SimpleDateFormat dateFormat;
    private final Context mContext;

    private int request_code;

    public DsAlgoLoader(@NonNull Context context, int tableNo) {
        super(context);
        if (websites.size() == 0) {
            websites.put("codechef.com", new Pair("Codechef", R.drawable.codechef));
            websites.put("codeforces.com", new Pair("Codeforces", R.drawable.codeforces));
            websites.put("leetcode.com", new Pair("Leetcode", R.drawable.leetcode));
            websites.put("atcoder.jp", new Pair("Atcoder", R.drawable.atcoder));
            websites.put("codingcompetitions.withgoogle.com", new Pair("Coding Competitions with Google", R.drawable.google));
        }
        request_code = 0;
        mContext = context;
        contests = new ArrayList<>();
        this.tableNo = tableNo;
        dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.getDefault());
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
            doc = Jsoup.connect("https://clist.by/").get();
            Element body = doc.select("div#contests").get(0);
            Elements contestsElem = body.select("div.row.contest");

            for (int i = 0; i < contestsElem.size(); i++) {
                Element row = contestsElem.get(i).select("div.col-md-7").get(0);
                Element contest = row.select("a[data-ace]").get(0);
                String jsonString = contest.attr("data-ace");

                JSONObject contestJSONObject = new JSONObject(jsonString);

                String website, name, contestLink, startingTime, endTime;
                website = contestJSONObject.getString("location");
                name = contestJSONObject.getString("title");
                contestLink = contestJSONObject.getString("desc").split(":", 2)[1].trim();

                JSONObject timeObject = contestJSONObject.getJSONObject("time");
                startingTime = timeObject.getString("start");
                endTime = timeObject.getString("end");

                Calendar cal = Calendar.getInstance();
                cal.setTime(dateFormat.parse(startingTime));
                cal.add(Calendar.HOUR, 5);
                cal.add(Calendar.MINUTE, 30);
                long timeDiff = (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
                if ((tableNo == 1 && timeDiff > 0))
                    break;
                else if ((tableNo == 2 && (timeDiff <= 0 || timeDiff / 2592000 > 1)) || !websites.containsKey(website))
                    continue;

                final Date stTime = cal.getTime();
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(dateFormat.parse(endTime));
                cal2.add(Calendar.HOUR, 5);
                cal2.add(Calendar.MINUTE, 30);
                final Date enTime = cal2.getTime();

                if (tableNo == 2 && timeDiff / 60 > 20)
                    setNotification(cal, websites.get(website).first, name);

                contests.add(new Contest(websites.get(website).second, name, stTime.toString(), enTime.toString(), contestLink));
            }

        } catch (IOException | ParseException | JSONException e) {
            Log.e("error", e.toString());
            e.printStackTrace();
        }

        return contests;
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
