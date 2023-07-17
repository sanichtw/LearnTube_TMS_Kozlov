package com.example.learntube.di.modules.data_sources

import com.example.learntube.data.remote.data_source.RemoteDataSourceImpl
import com.example.learntube.data.remote.SearchItemApi
import com.example.learntube.data.remote.data_source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Provides
    fun provideRemoteDataSource(searchItemApi: SearchItemApi): RemoteDataSource = RemoteDataSourceImpl(searchItemApi)
}