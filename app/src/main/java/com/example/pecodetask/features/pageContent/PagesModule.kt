package com.example.pecodetask.features.pageContent

import com.example.pecodetask.features.pageContent.data.NotificationManagerImpl
import com.example.pecodetask.features.pageContent.domain.NotificationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PagesModule {

    @Binds
    fun bindsNotificationManager(notificationManagerImpl: NotificationManagerImpl): NotificationManager


}