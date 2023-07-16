package com.example.learntube.data.local

import com.example.learntube.data.local.entity.SearchItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val searchItemDao: SearchItemDao) {

    suspend fun getAll(searchQuery: String?): List<SearchItemEntity> =
        searchItemDao.getAll(searchQuery)

    suspend fun getById(searchItemId: Long): SearchItemEntity? = searchItemDao.getById(searchItemId)

    suspend fun save(searchItems: List<SearchItemEntity>) = searchItemDao.save(searchItems)
    suspend fun save(searchItem: SearchItemEntity) = searchItemDao.save(searchItem)

//    suspend fun getFavouriteItems(): List<FavouriteItemEntity> = searchItemDao.getFavouriteItems()
    suspend fun setVideoAsFavorite(favouriteVideo: SearchItemEntity) =
        searchItemDao.setVideoAsFavorite(favouriteVideo = favouriteVideo)
}