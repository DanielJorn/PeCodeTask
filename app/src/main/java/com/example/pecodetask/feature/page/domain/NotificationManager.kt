package com.example.pecodetask.feature.page.domain

import com.example.pecodetask.feature.page.domain.model.NotificationData

interface NotificationManager {
    fun sendNotification(notificationData: NotificationData)
    fun deleteNotificationsFromPage(pageNumberToDelete: Int)
}