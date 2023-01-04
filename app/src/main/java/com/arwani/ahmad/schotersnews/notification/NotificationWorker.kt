package com.arwani.ahmad.schotersnews.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.arwani.ahmad.schotersnews.R
import com.arwani.ahmad.schotersnews.notification.NotificationConstant.NEWS_BOOKMARKED
import com.arwani.ahmad.schotersnews.notification.NotificationConstant.NEWS_ID
import com.arwani.ahmad.schotersnews.notification.NotificationConstant.NOTIFICATION_CHANNEL_ID
import com.arwani.ahmad.schotersnews.notification.NotificationConstant.NOTIF_UNIQUE_WORK
import com.arwani.ahmad.schotersnews.ui.favorite.FavoriteActivity

class NotificationWorker(ctx: Context, params: WorkerParameters) :  Worker(ctx, params) {

    private fun getPendingIntent(): PendingIntent? {
        val intent = Intent(applicationContext, FavoriteActivity::class.java)
        return TaskStackBuilder.create(applicationContext).run {
            addNextIntentWithParentStack(intent)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                getPendingIntent(0, PendingIntent.FLAG_MUTABLE)
            } else {
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }
        }
    }

    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
                .setContentIntent(getPendingIntent())
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(applicationContext.getString(R.string.notify_title))
                .setContentText(applicationContext.getString(R.string.notify_content))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIF_UNIQUE_WORK, NotificationManager.IMPORTANCE_HIGH)
            notification.setChannelId(NOTIFICATION_CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, notification.build())
    }
}