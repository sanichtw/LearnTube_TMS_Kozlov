package com.example.learntube.data.local.data_source

import com.example.learntube.data.local.SearchItemDao
import com.example.learntube.data.local.entity.SearchItemEntity
import javax.inject.Inject

internal class LocalDataSourceImpl @Inject constructor(
    private val searchItemDao: SearchItemDao
) : LocalDataSource {
    override suspend fun getAllSearchItems(searchQuery: String?): List<SearchItemEntity> =
        searchItemDao.getAllSearchItems(searchQuery)

    override suspend fun getFavouriteVideo(): List<SearchItemEntity> =
        searchItemDao.getFavouriteVideo()

    override suspend fun save(searchItems: List<SearchItemEntity>?) = searchItemDao.save(searchItems)
    override suspend fun save(searchItem: SearchItemEntity) = searchItemDao.save(searchItem)

    override suspend fun setVideoAsFavorite(favouriteVideo: SearchItemEntity?) =
        searchItemDao.setVideoAsFavorite(favouriteVideo = favouriteVideo)
}