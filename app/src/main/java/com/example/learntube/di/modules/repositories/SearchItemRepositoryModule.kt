package com.example.learntube.di.modules.repositories

import com.example.learntube.data.repository.SearchItemLocalRepository
import com.example.learntube.data.repository.SearchItemRemoteRepository
import com.example.learntube.data.repository.local.SearchItemDao
import com.example.learntube.domain.repository.SearchItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class SearchItemRepositoryModule {
    @Provides
    @Named("searchItemLocalRepository")
    fun provideSearchItemLocalRepository(@Named("searchItemLocalDao") searchItemDao: SearchItemDao): SearchItemRepository =
        SearchItemLocalRepository(searchItemDao)

    @Provides
    @Named("searchItemRemoteRepository")
    fun provideSearchItemRemoteRepository(@Named("searchItemRemoteDao") searchItemDao: SearchItemDao): SearchItemRepository =
        SearchItemRemoteRepository(searchItemDao)
}