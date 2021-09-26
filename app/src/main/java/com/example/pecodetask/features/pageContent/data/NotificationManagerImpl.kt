package com.example.pecodetask.features.pageContent.data

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.pecodetask.R
import com.example.pecodetask.features.pageContent.domain.NotificationManager
import com.example.pecodetask.features.pageContent.domain.model.NotificationData
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NotificationManager {

    private val androidNotificationManager
        get() =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager

    override fun sendNotification(notificationData: NotificationData) {
        if (needToCreateNotificationChannel())
            createNotificationChannel()

        val title = context.getString(R.string.notification_title)
        val text = context.getString(R.string.notification_text, notificationData.pageNumber)
        sendNotification(title, text, notificationData.pageNumber)
    }

    private fun sendNotification(
        title: String,
        text: String,
        pageNumber: Int,
    ) {
        val tag = "$pageNumber"
        val notificationId = Random().nextInt()
        val notification = createNotification(context, title, text)
        androidNotificationManager.notify(tag, notificationId, notification)
    }

    private fun createNotification(
        context: Context,
        title: String,
        text: String
    ): Notification {
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.pecode_logo)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            context.getString(R.string.app_name),
            android.app.NotificationManager.IMPORTANCE_DEFAULT
        )
        androidNotificationManager.createNotificationChannel(channel)
    }

    private fun needToCreateNotificationChannel() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "NotificationManagerImpl"
    }
}