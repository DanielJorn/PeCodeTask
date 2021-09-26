package com.example.pecodetask.feature.page.domain.usecase

import com.example.pecodetask.feature.page.domain.NotificationManager
import javax.inject.Inject

class DeleteNotificationsFromPageUseCase @Inject constructor(
    private val notificationManager: NotificationManager
){
    fun deleteNotificationsFromPage(pageNumber: Int) {
        notificationManager.deleteNotificationsFromPage(pageNumber)
    }

}
