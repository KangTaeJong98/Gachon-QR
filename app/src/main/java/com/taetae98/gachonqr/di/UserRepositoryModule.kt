package com.taetae98.gachonqr.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.taetae98.gachonqr.userDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserRepositoryModule {
    @Provides
    @Singleton
    @UserDataStore
    fun provideUserDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.userDataStore
    }
}