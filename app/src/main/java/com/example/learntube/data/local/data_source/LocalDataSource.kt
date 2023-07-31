package com.example.learntube.data.local.data_source

import com.example.learntube.data.local.entity.SearchItemEntity

internal interface LocalDataSource {
    suspend fun getAllSearchItems(searchQuery: String?): List<SearchItemEntity>
    suspend fun getFavouriteVideo(): List<SearchItemEntity>
    suspend fun save(searchItems: List<SearchItemEntity>?)
    suspend fun save(searchItem: SearchItemEntity)
    suspend fun setVideoAsFavorite(favouriteVideo: SearchItemEntity?)
}