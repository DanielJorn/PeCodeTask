package com.example.pecodetask.feature.page

import android.content.Context
import com.example.pecodetask.feature.page.data.notification.NotificationManagerImpl
import com.example.pecodetask.feature.page.domain.NotificationManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PagesModule {

    @Binds
    fun bindsNotificationManager(notificationManagerImpl: NotificationManagerImpl): NotificationManager

    companion object {
        @Provides
        fun providesAndroidNotificationManager(@ApplicationContext context: Context): android.app.NotificationManager {
            return context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
        }
    }
}