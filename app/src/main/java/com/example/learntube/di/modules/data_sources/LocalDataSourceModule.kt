package com.example.learntube.di.modules.data_sources

import com.example.learntube.data.local.data_source.LocalDataSourceImpl
import com.example.learntube.data.local.SearchItemDao
import com.example.learntube.data.local.data_source.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {
    @Provides
    fun provideLocalDataSource(itemDao: SearchItemDao): LocalDataSource = LocalDataSourceImpl(itemDao)
}