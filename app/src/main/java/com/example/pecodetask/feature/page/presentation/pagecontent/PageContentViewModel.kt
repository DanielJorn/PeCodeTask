package com.example.pecodetask.feature.page.presentation.pagecontent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pecodetask.feature.page.domain.usecase.SendNotificationUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class PageContentViewModel @AssistedInject constructor(
    @Assisted private val pageIndex: Int,
    private val sendNotificationUseCase: SendNotificationUseCase
) : ViewModel() {

    fun onNewNotificationClick() = sendNotificationUseCase.sendNotification(pageIndex)

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(pageIndex: Int): PageContentViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            pageIndex: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(pageIndex) as T
            }
        }
    }
}