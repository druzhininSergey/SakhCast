package com.example.sakhcast.data.firebase_messaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.sakhcast.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title ?: "Уведомление"
        val body = remoteMessage.notification?.body ?: "Новое сообщение"

        showNotification(title, body)
    }

    override fun onNewToken(token: String) {}

    private fun showNotification(title: String, body: String) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "Firebase_Channel"

        val channel = NotificationChannel(
            channelId,
            "Firebase Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_sakh_tv_logo)
            .setAutoCancel(true)

        notificationManager.notify(0, notificationBuilder.build())
    }
}