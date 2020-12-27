package com.educational.nsutresources.Notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.educational.nsutresources.Activity.CompetitiveProgrammingActivity;
import com.educational.nsutresources.R;

import static com.educational.nsutresources.Notifications.NotificationClass.CHANNEL_1_ID;

public class AlertReceiver extends BroadcastReceiver {

    private NotificationManagerCompat notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();

        String title = extras.getString("title");
        String desc = extras.getString("description");

        Intent resultIntent = new Intent(context, CompetitiveProgrammingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(desc)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .build();

        notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);
    }
}
