package com.example.learntube.di.modules.utils

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GlideModule {

    @Provides
    fun provideRequestManager(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
    }
}