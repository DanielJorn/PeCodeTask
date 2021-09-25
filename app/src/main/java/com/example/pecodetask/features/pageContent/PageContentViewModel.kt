package com.example.pecodetask.features.pageContent

import android.util.Log
import androidx.lifecycle.ViewModel

class PageContentViewModel: ViewModel() {

    fun onNewNotificationClick(){
        Log.d("TAG", "onNewNotificationClick: click")
    }
}