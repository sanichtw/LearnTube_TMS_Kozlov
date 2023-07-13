package com.example.learntube.di

import android.content.Context
import com.bumptech.glide.RequestManager
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.use_cases.SetToFavouriteVideosUseCase
import com.example.learntube.presentation.fragments.PostsScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

//    @Provides
//    fun provideContext(fragmentContext: PostsScreen): PostsScreen = fragmentContext

//    @Provides
//    fun provideSearchItems(searchItems: List<SearchItem>): List<SearchItem> = searchItems

//    @Provides
//    fun provideUseCase(useCase: SetToFavouriteVideosUseCase): SetToFavouriteVideosUseCase =
//        useCase
}