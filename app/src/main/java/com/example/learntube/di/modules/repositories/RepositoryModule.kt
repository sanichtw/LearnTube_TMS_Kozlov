package com.example.learntube.di.modules.repositories

import com.example.learntube.data.repository.PostsRepositoryImpl
import com.example.learntube.data.repository.local.PostsLocal
import com.example.learntube.data.repository.remote.PostsRemote
import com.example.learntube.domain.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun providePostsRepository(postsRemote: PostsRemote, postsLocal: PostsLocal): PostsRepository =
        PostsRepositoryImpl(postsRemote = postsRemote, postsLocal = postsLocal)
}