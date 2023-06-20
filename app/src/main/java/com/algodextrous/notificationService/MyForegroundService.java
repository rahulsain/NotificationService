package com.algodextrous.notificationService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.algodextrous.NotificationService.R;

public class MyForegroundService extends Service {

    private final String channelId = "ForegroundService_Channel";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // onStartCommand is creating a notification and starting foreground service (optional), replace your code
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("Foreground Service")
                .setContentText("Service is running...")
                .setSmallIcon(R.drawable.baseline_notifications_24);

        startForeground(1, notification.build());

        Log.v("MyForegroundService", "onStartCommandCalled");

        return START_NOT_STICKY;
    }

    // creating notification channel for foreground service (optional), replace your code to sync
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    channelId,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

}
