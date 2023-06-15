package com.algodextrous.notificationService;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    // onMessageReceived is called when a notification is received in background thread
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        // starting foreground service
        Intent intent = new Intent(this, MyForegroundService.class);
        String msg;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
            msg = "onMessageReceivedOreo";
        } else {
            startService(intent);
            msg = "onMessageReceivedLessThanOreo";
        }
        Log.d("MyFirebaseMessaging", msg);


        // sending toast on main ui thread
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
    }
}