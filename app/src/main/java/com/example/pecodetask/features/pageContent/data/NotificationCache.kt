package com.example.pecodetask.features.pageContent.data

import com.example.pecodetask.features.pageContent.domain.model.NotificationData

interface NotificationCache {
    fun addNewNotification(pageNumber: Long): NotificationData
}