package com.example.myapplication

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat

class AlertReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {

        val identifyStretching = intent.getIntExtra("identifyStretching",0)

        var notificationHelper: NotificationHelper = NotificationHelper(context)

        var nb: NotificationCompat.Builder = notificationHelper.getChannelNotification(identifyStretching)

        notificationHelper.getManager().notify(1, nb.build())
        Log.d("abc", "abc")
    }
}