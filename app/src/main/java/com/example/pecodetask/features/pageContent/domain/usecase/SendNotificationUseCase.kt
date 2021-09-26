package com.example.pecodetask.features.pageContent.domain.usecase

import com.example.pecodetask.features.pageContent.domain.NotificationManager
import com.example.pecodetask.features.pageContent.domain.model.NotificationData
import javax.inject.Inject

class SendNotificationUseCase @Inject constructor(
    private val notificationManager: NotificationManager
) {
    fun sendNotification(pageNumber: Int) {
        notificationManager.sendNotification(NotificationData(pageNumber))
    }
}
