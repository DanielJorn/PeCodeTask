package com.example.pecodetask.feature.page.domain.usecase

import com.example.pecodetask.feature.page.domain.NotificationManager
import com.example.pecodetask.feature.page.domain.model.NotificationData
import javax.inject.Inject

class SendNotificationUseCase @Inject constructor(
    private val notificationManager: NotificationManager
) {
    fun sendNotification(pageNumber: Int) {
        notificationManager.sendNotification(NotificationData(pageNumber))
    }
}
