package com.example.pecodetask.features.pageContent.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageContentViewModelFactory(private val pageNumber: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PageContentViewModel(pageNumber) as T
    }
}