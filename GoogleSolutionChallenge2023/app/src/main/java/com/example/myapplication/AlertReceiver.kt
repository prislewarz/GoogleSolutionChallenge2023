package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat

class AlertReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MYtag","확인2")
        var notificationHelper: NotificationHelper = NotificationHelper(context)
        var time = intent?.extras?.getString("time")
        var nb: NotificationCompat.Builder = notificationHelper.getChannelNotification(time)

        notificationHelper.getManager().notify(1, nb.build())
    }
}