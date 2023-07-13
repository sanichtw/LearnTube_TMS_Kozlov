package com.example.learntube.domain.repository

import com.example.learntube.data.local.entity.FavouriteItemEntity
import com.example.learntube.domain.models.SearchItem

interface SearchItemRepository {

    suspend fun getSearchItems(searchQuery: String?): List<SearchItem>

    suspend fun getSearchItemById(searchItemId: Long): SearchItem?

    suspend fun getFavouriteItems(): List<FavouriteItemEntity>

    suspend fun setToFavouriteVideo(favouriteVideo: SearchItem)
}