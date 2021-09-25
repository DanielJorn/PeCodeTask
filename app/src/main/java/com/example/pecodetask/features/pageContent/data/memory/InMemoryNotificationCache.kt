package com.example.pecodetask.features.pageContent.data.memory

import com.example.pecodetask.features.pageContent.data.NotificationCache
import com.example.pecodetask.features.pageContent.domain.model.NotificationData
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryNotificationCache @Inject constructor(): NotificationCache {
    private val notificationsFromPages = hashMapOf<Long, MutableList<Int>>()

    override fun addNewNotification(pageNumber: Long): NotificationData {
        val newNotificationId = Random().nextInt()
        putNotificationIdInMap(pageNumber, newNotificationId)
        return NotificationData(newNotificationId, pageNumber)
    }

    override fun getNotificationCreatedByPageNumber(pageNumber: Int): List<Int> {
        return when(val notificationsByPageNumber = notificationsFromPages[pageNumber.toLong()]) {
            null -> listOf()
            else -> notificationsByPageNumber.toList()
        }
    }

    private fun putNotificationIdInMap(pageNumber: Long, newNotificationId: Int) {
        val notificationList = notificationsFromPages[pageNumber]
        if (notificationList == null)
            notificationsFromPages[pageNumber] = mutableListOf(newNotificationId)
        else
            notificationList.add(newNotificationId)
    }
}