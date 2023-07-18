package com.example.learntube.di.base

import android.content.Context
import androidx.room.Room
import com.example.learntube.data.local.SearchItemDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): SearchItemDataBase =
        Room
            .databaseBuilder(
                context,
                SearchItemDataBase::class.java,
                "posts_db" //TODO add const - const val
            )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(db: SearchItemDataBase) = db.searchItemDao()

//    @Provides
//    fun provideEntity() = SearchItemEntity()
}