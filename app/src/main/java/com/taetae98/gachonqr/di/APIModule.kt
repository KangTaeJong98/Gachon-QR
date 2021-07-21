package com.taetae98.gachonqr.di

import com.taetae98.gachonqr.api.LoginAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModule {
    @Singleton
    @Provides
    fun provideLoginAPI(): LoginAPI {
        return LoginAPI.getInstance()
    }
}