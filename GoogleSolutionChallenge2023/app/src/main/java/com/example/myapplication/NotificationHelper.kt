package com.example.myapplication

import NeckSetFragment
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class NotificationHelper(base: Context?) : ContextWrapper(base){
    private val channelID = "channelID"
    private val channelNm = "channelNm"

    init{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel()
        }
    }

    private fun createChannel(){
        var channel = NotificationChannel(channelID, channelNm, NotificationManager.IMPORTANCE_DEFAULT)

        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = Color.GREEN
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        getManager().createNotificationChannel(channel)

    }

    fun getManager() : NotificationManager{
        return getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    fun getChannelNotification(time:String?):NotificationCompat.Builder{

        val intent = Intent(this, NeckStretchingStart::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0 , intent , 0)

        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(getString(R.string.neck_notification_title))
            .setContentText(getString(R.string.neck_notification_content))
            .setSmallIcon(R.drawable.neck)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

    }
}