package com.example.learntube.domain.repositories

import com.example.learntube.domain.models.SearchItem

internal interface SearchItemRepository {
    suspend fun getSearchItems(searchQuery: String?): List<SearchItem>?
    suspend fun getFavouriteVideos(): List<SearchItem>
    suspend fun setVideoAsFavorite(favouriteVideo: SearchItem?)
}