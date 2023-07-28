package com.example.learntube.di.modules.utils

import android.content.Context
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.presentation.adapters.SearchItemAdapter
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted

//@Module
//internal class SearchItemsAdapterModule {
//
//    @Provides
//    fun provideSearchItemAdapter(
//        context: Context,
//        onCheckedChanged: (item: SearchItem) -> Unit,
//        @Assisted searchItems: List<SearchItem> // Указали @Assisted для searchItems
//    ): SearchItemAdapter {
//        return SearchItemAdapter(context, onCheckedChanged, searchItems)
//    }
//
//    // Предоставляйте вашу фабрику
//    @Provides
//    fun provideSearchItemAdapterFactory(): SearchItemAdapterFactory {
//        return object : SearchItemAdapterFactory {
//            override fun create(searchItems: List<SearchItem>): SearchItemAdapter {
//                return SearchItemAdapter(searchItems)
//            }
//        }
//    }
//}