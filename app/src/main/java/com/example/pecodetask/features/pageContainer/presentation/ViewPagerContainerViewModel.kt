package com.example.pecodetask.features.pageContainer.presentation

import androidx.lifecycle.ViewModel
import com.example.pecodetask.features.pageContainer.domain.usecase.DeleteNotificationsFromPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewPagerContainerViewModel @Inject constructor(
    private val deleteNotificationsFromPageUseCase: DeleteNotificationsFromPageUseCase
) : ViewModel() {

    fun onMinusButtonClicked(lastPageNumber: Int) {
        deleteNotificationsFromPageUseCase.deleteNotificationsFromPage(lastPageNumber)
    }
}