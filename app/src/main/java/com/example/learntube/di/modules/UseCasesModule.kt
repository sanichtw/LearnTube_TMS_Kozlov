package com.example.learntube.di.modules

import com.example.learntube.domain.repository.SearchItemRepository
import com.example.learntube.domain.use_cases.GetPostInfoUseCase
import com.example.learntube.domain.use_cases.GetPostsBySearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {
    @Provides
    fun provideGetPostInfoUseCase(searchItemRepository: SearchItemRepository): GetPostInfoUseCase =
        GetPostInfoUseCase(searchItemRepository = searchItemRepository)

    @Provides
    fun provideGetPostsBySearch(searchItemRepository: SearchItemRepository): GetPostsBySearchUseCase =
        GetPostsBySearchUseCase(searchItemRepository = searchItemRepository)
}