package com.educational.nsutresources.Notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

public class NotificationClass extends Application {

    public static final String CHANNEL_1_ID = "Contests";
    public static NotificationManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        createChannel();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Contests",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            channel.setDescription("This is Contests channel");

            manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
