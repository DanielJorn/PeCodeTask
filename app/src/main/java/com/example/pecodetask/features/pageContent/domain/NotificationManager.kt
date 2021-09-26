package com.example.pecodetask.features.pageContent.domain

import com.example.pecodetask.features.pageContent.domain.model.NotificationData

interface NotificationManager {
    fun sendNotification(notificationData: NotificationData)
    fun deleteNotificationsFromPage(pageNumberToDelete: Int)
}