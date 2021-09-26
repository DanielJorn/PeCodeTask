package com.example.pecodetask.feature.page.data.notification

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.service.notification.StatusBarNotification
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.pecodetask.R
import com.example.pecodetask.feature.page.domain.NotificationManager
import com.example.pecodetask.feature.page.domain.model.NotificationData
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val androidNotificationManager: android.app.NotificationManager
) : NotificationManager {

    private val activeNotifications get() = androidNotificationManager.activeNotifications

    override fun sendNotification(notificationData: NotificationData) {
        if (needToCreateNotificationChannel())
            createNotificationChannel()

        val title = context.getString(R.string.notification_title)
        val text = context.getString(R.string.notification_text, notificationData.pageNumber)
        sendNotification(title, text, notificationData.pageNumber)
    }

    override fun deleteNotificationsFromPage(pageNumberToDelete: Int) {
        activeNotifications
            .filter { wasNotificationSentFromPage(it, pageNumberToDelete) }
            .forEach { cancelNotification(it.tag, it.id) }
    }

    private fun sendNotification(title: String, text: String, pageNumber: Int) {
        val tag = "$pageNumber"
        val notificationId = Random().nextInt()
        val notification = createNotification(context, title, text, pageNumber)
        androidNotificationManager.notify(tag, notificationId, notification)
    }

    private fun createNotification(
        context: Context,
        title: String,
        text: String,
        pageNumber: Int
    ): Notification {
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val intent = constructIntent(pageNumber)

        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.pecode_logo)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(intent)
            .build()
    }

    private fun constructIntent(selectedPageNumber: Int) = NavDeepLinkBuilder(context)
        .setGraph(R.navigation.nav_graph)
        .setDestination(R.id.containerFragment)
        .setArguments(Bundle().apply { putInt("selectedPageNumber", selectedPageNumber) })
        .createPendingIntent()

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

    private fun wasNotificationSentFromPage(
        notification: StatusBarNotification, pageNumber: Int
    ) = notification.tag == "$pageNumber"

    private fun cancelNotification(tag: String, notificationId: Int) {
        androidNotificationManager.cancel(tag, notificationId)
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "NotificationManagerImpl"
    }
}