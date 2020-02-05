package com.educational.nsutresources.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.educational.nsutresources.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ContestDetailsActivity extends AppCompatActivity {

    private TextView contestName;
    private TextView contestStartTime;
    private TextView contestEndTime;
    private TextView timeLeft;
    private ImageView contestImage;

    private String contestUrlToBrowse;
    private String startDateToCalender;
    private String endDateToCalender;
    private String contestNameToCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_details);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        Toolbar toolbar = findViewById(R.id.contestdetails_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contest Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contestName = findViewById(R.id.contest_name);
        contestStartTime = findViewById(R.id.contest_start_time);
        contestEndTime = findViewById(R.id.contest_endtime);
        timeLeft = findViewById(R.id.time_left);
        contestImage = findViewById(R.id.contest_img);

        if (intent.hasExtra("contest_name")) {
            contestNameToCalender = intent.getStringExtra("contest_name");
            contestName.setText(contestNameToCalender);

            Date startDate = new Date(intent.getStringExtra("contest_start_date"));
            startDateToCalender = intent.getStringExtra("contest_start_date");

            contestStartTime.setText((formatDate(startDate) + ", " + formatTime(startDate)));
            endDateToCalender = intent.getStringExtra("contest_end_date");

            Date endDate = new Date(endDateToCalender);
            contestEndTime.setText(formatDate(endDate) + ", " + formatTime(endDate));

            contestUrlToBrowse = (intent.getStringExtra("contest_url"));

            contestImage.setImageResource(intent.getIntExtra("contest_img", 0));

            final Date current = Calendar.getInstance().getTime();
            final Date stTime = new Date(startDateToCalender);
            if (stTime.getTime() - current.getTime() >= 0) {
                timeLeft.setText(printDifference(current, startDate));
            } else {
                timeLeft.setText("00:00");
            }
        }
    }

    public String printDifference(Date startDate, Date endDate) {

        long difference = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = difference / daysInMilli;
        difference = difference % daysInMilli;

        long elapsedHours = difference / hoursInMilli;
        difference = difference % hoursInMilli;

        long elapsedMinutes = difference / minutesInMilli;
        difference = difference % minutesInMilli;

        long elapsedSeconds = difference / secondsInMilli;

        return (elapsedDays + "days, " + elapsedHours + "h, " + elapsedMinutes + "m, " + elapsedSeconds + "s");
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contest_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();
        switch (itemid) {
            case R.id.share_contest:
                shareContest();
                break;

            case R.id.browse_contest:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(contestUrlToBrowse));
                startActivity(intent);
                break;

            case R.id.setReminder:
                onAddEventClicked();
                break;

            case android.R.id.home:
                    onBackPressed();
        }

        return true;
    }

    private void shareContest() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "I invite to participate in: " + contestNameToCalender +
                " Check it out: " + contestUrlToBrowse);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Send to"));

    }

    public void onAddEventClicked() {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        intent.putExtra("beginTime", (new Date(startDateToCalender)).getTime());
        intent.putExtra("endTime", (new Date(endDateToCalender)).getTime());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(Events.TITLE, contestNameToCalender);
        startActivity(intent);
    }
}
