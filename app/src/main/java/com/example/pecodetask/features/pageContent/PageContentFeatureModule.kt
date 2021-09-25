package com.example.pecodetask.features.pageContent

import com.example.pecodetask.features.pageContent.data.NotificationCache
import com.example.pecodetask.features.pageContent.data.NotificationRepositoryImpl
import com.example.pecodetask.features.pageContent.data.memory.InMemoryNotificationCache
import com.example.pecodetask.features.pageContent.domain.NotificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface PageContentFeatureModule {

    @Binds
    fun bindsNotificationMemoryCache(inMemoryNotificationCache: InMemoryNotificationCache): NotificationCache

    @Binds
    fun bindsNotificationRepository(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationRepository
}