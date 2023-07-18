package com.example.learntube.di.modules.repositories

import com.example.learntube.data.local.data_source.LocalDataSource
import com.example.learntube.data.remote.data_source.RemoteDataSource
import com.example.learntube.data.repository.SearchItemRepositoryImpl
import com.example.learntube.domain.repositories.SearchItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class SearchItemRepositoryModule {
    @Provides
    fun provideRepositoryModule(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): SearchItemRepository =
        SearchItemRepositoryImpl(remoteDataSource = remoteDataSource, localDataSource = localDataSource)
}