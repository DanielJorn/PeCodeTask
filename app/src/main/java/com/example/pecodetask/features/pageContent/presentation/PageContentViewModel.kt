package com.example.pecodetask.features.pageContent.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pecodetask.features.pageContent.domain.model.NotificationData
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class PageContentViewModel @AssistedInject constructor(
    @Assisted private val pageNumber: Long
) : ViewModel() {

    private val _notificationClick = MutableLiveData<NotificationData>()

    val notificationClick: LiveData<NotificationData> get() = _notificationClick
    fun onNewNotificationClick() {
        _notificationClick.value = NotificationData(pageNumber)
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(itemId: Long): PageContentViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            itemId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(itemId) as T
            }
        }
    }
}