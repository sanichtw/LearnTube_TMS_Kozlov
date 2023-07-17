package com.example.learntube.data.repository

import com.example.learntube.data.local.data_source.LocalDataSource
import com.example.learntube.data.local.entity.SearchItemEntity
import com.example.learntube.data.local.entity.toModel
import com.example.learntube.data.remote.data_source.RemoteDataSource
import com.example.learntube.data.remote.dto.toEntity
import com.example.learntube.data.remote.dto.toModel
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.models.toEntity
import com.example.learntube.domain.repositories.SearchItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchItemRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : SearchItemRepository {
    override suspend fun getSearchItems(searchQuery: String?): List<SearchItem> {
        val localItems: List<SearchItemEntity> = localDataSource.getAllSearchItems(searchQuery)

        return if (localItems.isEmpty()) {
            val remoteItems = remoteDataSource.fetchItems(searchQuery)
            localDataSource.save(remoteItems.map { it.toEntity(searchQuery) })
            remoteItems.map { it.toModel(searchQuery) }
        } else {
            localItems.map { it.toModel(searchQuery) }
        }
    }

    override suspend fun getFavouriteVideo(): List<SearchItem> {
        val mappedToSearchItemFavouriteVideo =
            localDataSource.getFavouriteVideo().map { favouriteVideo ->
                favouriteVideo.toModel()
            }
        return mappedToSearchItemFavouriteVideo
    }

    override suspend fun setVideoAsFavorite(favouriteVideo: SearchItem) {
        localDataSource.setVideoAsFavorite(favouriteVideo.toEntity())
    }
}