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

    fun getChannelNotification(identifyStretching : Int):NotificationCompat.Builder{

        val neckIntent = Intent(this, NeckStretchingStart::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val eyeIntent = Intent(this, EyeStretchingStart::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        if(identifyStretching == 1){
            val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0 , neckIntent , PendingIntent.FLAG_IMMUTABLE)
            return NotificationCompat.Builder(applicationContext, channelID)
                .setContentTitle(getString(R.string.neck_notification_title))
                .setContentText(getString(R.string.neck_notification_content))
                .setSmallIcon(R.drawable.neck)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
        else{
            val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0 , eyeIntent , PendingIntent.FLAG_IMMUTABLE)
            Log.d("abc", "111")
            return NotificationCompat.Builder(applicationContext, channelID)
                .setContentTitle(getString(R.string.eye_notification_title))
                .setContentText(getString(R.string.eye_notification_content))
                .setSmallIcon(R.drawable.eye)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        }




    }
}