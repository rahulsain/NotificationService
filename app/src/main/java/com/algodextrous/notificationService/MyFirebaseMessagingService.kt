package com.algodextrous.notificationService

import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val intent = Intent(this, MyForegroundService::class.java)
        val msg = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
            "onMessageReceivedOreo"
        } else {
            startService(intent)
            "onMessageReceivedLessThanOreo"
        }
        Log.d("MyFirebaseMessaging", msg)

        Handler(Looper.getMainLooper()).post {
            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}