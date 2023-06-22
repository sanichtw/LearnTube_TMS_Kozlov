package com.example.learntube.di.modules.repositories

import com.example.learntube.data.remote.api.SearchItemApi
import com.example.learntube.data.repository.remote.PostsRemote
import com.example.learntube.data.repository.remote.PostsRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteRepositoryModule {
    @Provides
    fun providePostsRemote(searchItemApi: SearchItemApi): PostsRemote =
        PostsRemoteImpl(searchItemApi = searchItemApi)
}