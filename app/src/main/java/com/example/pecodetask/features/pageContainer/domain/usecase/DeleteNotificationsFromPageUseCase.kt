package com.example.pecodetask.features.pageContainer.domain.usecase

import com.example.pecodetask.features.pageContent.domain.NotificationManager
import javax.inject.Inject

class DeleteNotificationsFromPageUseCase @Inject constructor(
    private val notificationManager: NotificationManager
){
    fun deleteNotificationsFromPage(pageNumber: Int) {
        notificationManager.deleteNotificationsFromPage(pageNumber)
    }

}
