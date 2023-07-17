package com.example.learntube.domain.repository

import com.example.learntube.domain.models.SearchItem

interface SearchItemRepository {

    suspend fun getSearchItems(searchQuery: String?): List<SearchItem>

    suspend fun getSearchItemById(etag: String): SearchItem?

//    suspend fun getFavouriteItems(): List<FavouriteItemEntity>

    suspend fun setVideoAsFavorite(favouriteVideo: SearchItem)
}