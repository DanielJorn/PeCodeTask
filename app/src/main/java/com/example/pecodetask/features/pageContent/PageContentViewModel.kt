package com.example.pecodetask.features.pageContent

import android.util.Log
import androidx.lifecycle.ViewModel

class PageContentViewModel(private val pageNumber: Long): ViewModel() {

    fun onNewNotificationClick(){
        Log.d("TAG", "onNewNotificationClick: $pageNumber")
    }
}