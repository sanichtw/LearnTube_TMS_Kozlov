package com.example.learntube.di.modules.repositories

import com.example.learntube.data.local.LocalDataSource
import com.example.learntube.data.local.SearchItemDao
import com.example.learntube.data.remote.RemoteDataSource
import com.example.learntube.data.remote.SearchItemApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalRepositoryModule {
    @Provides
    fun provideLocalDataSource(itemDao: SearchItemDao): LocalDataSource = LocalDataSource(itemDao)

    @Provides
    fun provideRemoteDataSource(searchItemApi: SearchItemApi): RemoteDataSource = RemoteDataSource(searchItemApi)
}