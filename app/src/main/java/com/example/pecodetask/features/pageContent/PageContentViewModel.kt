package com.example.pecodetask.features.pageContent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PageContentViewModel(private val pageNumber: Long): ViewModel() {

    private val _notificationClick = MutableLiveData<NotificationData>()
    val notificationClick: LiveData<NotificationData> get() = _notificationClick

    fun onNewNotificationClick(){
        _notificationClick.value = NotificationData("title","text")
    }
}