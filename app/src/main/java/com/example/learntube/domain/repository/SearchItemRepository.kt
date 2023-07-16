package com.example.learntube.domain.repository

import com.example.learntube.domain.models.SearchItem
import kotlinx.coroutines.flow.Flow

interface SearchItemRepository {

    suspend fun getSearchItems(searchQuery: String?): List<SearchItem>

    suspend fun getSearchItemById(searchItemId: Long): SearchItem?

//    suspend fun getFavouriteItems(): List<FavouriteItemEntity>

    suspend fun setVideoAsFavorite(favouriteVideo: SearchItem)
}