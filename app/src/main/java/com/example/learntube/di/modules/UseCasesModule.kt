package com.example.learntube.di.modules

import com.example.learntube.data.repository.SearchItemRepositoryImpl
import com.example.learntube.domain.use_cases.GetSearchItemByIdUseCase
import com.example.learntube.domain.use_cases.GetSearchItemsBySearchQueryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {
    @Provides
    fun provideSearchItemById(searchItemRepository: SearchItemRepositoryImpl): GetSearchItemByIdUseCase =
        GetSearchItemByIdUseCase(searchItemRepository = searchItemRepository)

    @Provides
    fun provideSearchItemsBySearch(searchItemRepository: SearchItemRepositoryImpl): GetSearchItemsBySearchQueryUseCase =
        GetSearchItemsBySearchQueryUseCase(searchItemRepository = searchItemRepository)
}