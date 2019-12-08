package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nsutallin1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ContestDetailsActivity extends AppCompatActivity {

    private TextView contestName;
    private TextView contestNameDesc;
    private TextView contestStartTime;
    private TextView contestStartTimeDesc;
    private TextView contestEndTime;
    private TextView contestEndTimeDesc;
    private TextView contestUrl;
    private TextView contestUrlDesc;
    private ImageView contestImage;

    private String contestUrlToBrowse;
    private String startDateToCalender;
    private String contestNameToCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_details);

        Intent intent=getIntent();
        Toolbar toolbar=findViewById(R.id.contestdetails_toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contest Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contestName=findViewById(R.id.contest_name);
        contestNameDesc=findViewById(R.id.contest_name_desc);

        contestStartTime=findViewById(R.id.contest_start_time);
        contestStartTimeDesc=findViewById(R.id.contest_start_time_desc);

        contestEndTime=findViewById(R.id.contest_endtime);
        contestEndTimeDesc=findViewById(R.id.contest_endtime_desc);

        contestUrl=findViewById(R.id.url);
        contestUrlDesc=findViewById(R.id.url_desc);

        contestImage=findViewById(R.id.contest_img);

        if (intent.hasExtra("contest_name"))
        {
            contestNameToCalender=intent.getStringExtra("contest_name");
            contestName.setText(contestNameToCalender);
            contestNameDesc.setTextColor(getResources().getColor(R.color.pink));

            Date startDate= new Date(intent.getStringExtra("contest_start_date"));
            startDateToCalender=intent.getStringExtra("contest_start_date");
            Log.e("start time",startDateToCalender);
            contestStartTime.setText((formatDate(startDate) + ", " + formatTime(startDate)));
            contestStartTimeDesc.setTextColor(getResources().getColor(R.color.pink));
            Date endDate= new Date(intent.getStringExtra("contest_end_date"));
            contestEndTime.setText(formatDate(endDate) + ", " + formatTime(endDate));
            contestEndTimeDesc.setTextColor(getResources().getColor(R.color.pink));
            contestUrlToBrowse=(intent.getStringExtra("contest_url"));
            String [] resultUrl=contestUrlToBrowse.split("\\?");
            String [] someUrl=resultUrl[0].split("//",2);
            contestImage.setImageResource(intent.getIntExtra("contest_img",0));
            contestUrl.setText(someUrl[1]);
            contestUrlDesc.setTextColor(getResources().getColor(R.color.pink));
        }
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
        getMenuInflater().inflate(R.menu.contest_details_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid=item.getItemId();
        switch (itemid)
        {
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
        }

        return true;
    }

    private void shareContest() {

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,"I invite to participate in: "+ contestNameToCalender+
                " Check it out: "+ contestUrlToBrowse);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent,"Send to"));

    }

    public void onAddEventClicked() {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long endTime = cal.getTimeInMillis() + 60 * 60 * 1000;

        Log.e("Start date to calender",startDateToCalender);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,startDateToCalender);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(Events.TITLE, contestNameToCalender);
        startActivity(intent);
    }
}
