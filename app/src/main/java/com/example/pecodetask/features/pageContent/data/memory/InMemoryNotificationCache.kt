package com.example.pecodetask.features.pageContent.data.memory

import com.example.pecodetask.features.pageContent.data.NotificationCache
import com.example.pecodetask.features.pageContent.domain.model.NotificationData
import java.util.*
import javax.inject.Inject

class InMemoryNotificationCache @Inject constructor(): NotificationCache {
    private val notificationsFromPages = hashMapOf<Long, MutableList<Int>>()

    override fun addNewNotification(pageNumber: Long): NotificationData {
        val newNotificationId = Random().nextInt()
        putNotificationIdInMap(pageNumber, newNotificationId)
        return NotificationData(pageNumber)
    }

    private fun putNotificationIdInMap(pageNumber: Long, newNotificationId: Int) {
        val notificationList = notificationsFromPages[pageNumber]
        if (notificationList == null)
            notificationsFromPages[pageNumber] = mutableListOf(newNotificationId)
        else
            notificationList.add(newNotificationId)
    }
}