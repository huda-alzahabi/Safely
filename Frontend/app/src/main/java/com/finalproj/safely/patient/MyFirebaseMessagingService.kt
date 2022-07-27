package com.finalproj.safely.patient

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.finalproj.safely.user.MainActivity
import com.finalproj.safely.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val id = "notification_channel"

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {

        super.onNewToken(token)
        Log.d("newToken", token);

    }

    fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteView = RemoteViews("com.finalproj.safely", R.layout.notification)
        remoteView.setTextViewText(R.id.notification_title, title)
        remoteView.setTextViewText(R.id.notification_message, message)
        remoteView.setImageViewResource(R.id.app_logo, R.drawable.minilogo)
        return remoteView
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if(remoteMessage.getNotification()!=null){
            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
        }
    }

    //generate the notification
    fun generateNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)


        var notification = NotificationCompat.Builder(this, id)
            .setContentTitle(title)
            .setContentText(message)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setSmallIcon(R.drawable.minilogo)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        notification = notification.setContent(getRemoteView(title, message))
        val noticationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(id, "Safely", NotificationManager.IMPORTANCE_HIGH)
            noticationManager.createNotificationChannel(channel)
        }
        noticationManager.notify(0, notification.build())
    }
}