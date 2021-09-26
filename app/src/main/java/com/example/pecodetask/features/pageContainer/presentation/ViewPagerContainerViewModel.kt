package com.example.pecodetask.features.pageContainer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewPagerContainerViewModel @Inject constructor() : ViewModel() {
    private val _notificationsToDismiss = MutableLiveData<Int>()
    val notificationsToDismiss: LiveData<Int> get() = _notificationsToDismiss

    fun onMinusButtonClicked(pageNumber: Int) {
        _notificationsToDismiss.value = pageNumber
    }
}