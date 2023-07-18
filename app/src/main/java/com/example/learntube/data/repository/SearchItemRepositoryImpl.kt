package com.example.learntube.data.repository

import com.example.learntube.data.local.data_source.LocalDataSource
import com.example.learntube.data.local.entity.SearchItemEntity
import com.example.learntube.data.local.entity.toModel
import com.example.learntube.data.remote.data_source.RemoteDataSource
import com.example.learntube.data.remote.dto.mapToSearchItemDomain
import com.example.learntube.data.remote.dto.mapToSearchItemEntity
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.models.mapToSearchItemEntity
import com.example.learntube.domain.repositories.SearchItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SearchItemRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : SearchItemRepository {
    override suspend fun getSearchItems(searchQuery: String?): List<SearchItem> {
        val localItems: List<SearchItemEntity> = localDataSource.getAllSearchItems(searchQuery)

        return if (localItems.isEmpty()) {
            val remoteItems = remoteDataSource.fetchItems(searchQuery)
            localDataSource.save(remoteItems.map { remoteItem ->
                remoteItem.mapToSearchItemEntity(
                    searchQuery
                )
            })
            remoteItems.map { remoteItem -> remoteItem.mapToSearchItemDomain(searchQuery) }
        } else {
            localItems.map { localItem -> localItem.toModel(searchQuery) }
        }
    }

    override suspend fun getFavouriteVideo(): List<SearchItem> =
        localDataSource.getFavouriteVideo().map { favouriteVideo -> favouriteVideo.toModel() }

    override suspend fun setVideoAsFavorite(favouriteVideo: SearchItem) =
        localDataSource.setVideoAsFavorite(favouriteVideo.mapToSearchItemEntity())
}