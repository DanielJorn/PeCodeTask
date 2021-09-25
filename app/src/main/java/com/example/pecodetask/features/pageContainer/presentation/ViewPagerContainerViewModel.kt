package com.example.pecodetask.features.pageContainer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pecodetask.features.pageContent.domain.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewPagerContainerViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
): ViewModel() {
    private val _notificationsToDismiss = MutableLiveData<List<Int>>()
    val notificationsToDismiss: LiveData<List<Int>> get() = _notificationsToDismiss

    fun onMinusButtonClicked(pageNumber: Int) {
        val notifications: List<Int> = notificationRepository.getNotificationsCreatedByPageNumber(pageNumber)
          _notificationsToDismiss.value = notifications
    }
}