package com.example.pecodetask.features.pageContent.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pecodetask.features.pageContent.domain.model.NotificationData
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.*

class PageContentViewModel @AssistedInject constructor(
    @Assisted private val pageNumber: Int
) : ViewModel() {

    private val _notificationClick = MutableLiveData<NotificationData>()
    val onNotificationClick: LiveData<NotificationData> get() = _notificationClick

    fun onNewNotificationClick() {
        val id = Random().nextInt()
        _notificationClick.postValue(NotificationData(id, pageNumber))
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(itemId: Int): PageContentViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            itemId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(itemId) as T
            }
        }
    }
}