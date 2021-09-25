package com.example.pecodetask.features.pageContent.domain

import com.example.pecodetask.features.pageContent.domain.model.NotificationData

interface NotificationRepository {
    fun saveNewNotification(pageNumber: Long): NotificationData
}
