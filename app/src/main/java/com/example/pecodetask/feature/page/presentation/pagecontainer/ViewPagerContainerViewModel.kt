package com.example.pecodetask.feature.page.presentation.pagecontainer

import androidx.lifecycle.ViewModel
import com.example.pecodetask.feature.page.domain.usecase.DeleteNotificationsFromPageUseCase
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