package com.example.pecodetask.features.pageContent.data

import com.example.pecodetask.features.pageContent.data.memory.InMemoryNotificationCache
import com.example.pecodetask.features.pageContent.domain.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationCache: InMemoryNotificationCache
) : NotificationRepository {
    override fun saveNewNotification(pageNumber: Long) =
        notificationCache.addNewNotification(pageNumber)
}