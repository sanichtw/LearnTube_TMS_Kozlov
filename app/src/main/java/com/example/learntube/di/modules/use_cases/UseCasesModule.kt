package com.example.learntube.di.modules.use_cases

import com.example.learntube.data.repository.SearchItemRepositoryImpl
import com.example.learntube.domain.use_cases.GetFavoriteVideoUseCase
import com.example.learntube.domain.use_cases.GetSearchItemsBySearchQueryUseCase
import com.example.learntube.domain.use_cases.SetVideoAsFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {
    @Provides
    fun provideGetFavouriteVideoUseCase(searchItemRepository: SearchItemRepositoryImpl): GetFavoriteVideoUseCase =
        GetFavoriteVideoUseCase(searchItemRepository = searchItemRepository)

    @Provides
    fun provideSearchItemsBySearch(searchItemRepository: SearchItemRepositoryImpl): GetSearchItemsBySearchQueryUseCase =
        GetSearchItemsBySearchQueryUseCase(searchItemRepository = searchItemRepository)

    @Provides
    fun provideSetVideoAsFavoriteUseCase(searchItemRepository: SearchItemRepositoryImpl): SetVideoAsFavoriteUseCase =
        SetVideoAsFavoriteUseCase(searchItemRepository = searchItemRepository)
}