package com.example.learntube.di.modules.repositories

import com.example.learntube.data.local.SearchItemLocalDao
import com.example.learntube.data.repository.local.SearchItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalRepositoryModule {
    @Provides
    fun providePostsLocal(searchItemDao: SearchItemDao): com.example.learntube.data.repository.local.SearchItemDao =
        SearchItemLocalDao(searchItemLocalDao = searchItemDao)
}