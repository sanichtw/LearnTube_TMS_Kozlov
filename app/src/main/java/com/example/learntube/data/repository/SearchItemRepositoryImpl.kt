package com.example.learntube.data.repository

import android.util.Log
import com.example.learntube.data.local.LocalDataSource
import com.example.learntube.data.local.entity.SearchItemEntity
import com.example.learntube.data.local.entity.toModel
import com.example.learntube.data.remote.RemoteDataSource
import com.example.learntube.data.remote.dto.toEntity
import com.example.learntube.data.remote.dto.toModel
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.models.toEntity
import com.example.learntube.domain.repository.SearchItemRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchItemRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : SearchItemRepository {
    override suspend fun getSearchItems(searchQuery: String?): List<SearchItem> {
        Log.d("Test Adapter", "Called getSearchItems function")

        val localItems: List<SearchItemEntity> = localDataSource.getAll(searchQuery)
        println(localItems)

        runBlocking {
            println(localItems)
        }

        return if (localItems.isEmpty()) {
            val remoteItems = remoteDataSource.fetchItems(searchQuery)
            println("printLn local: $remoteItems")
            localDataSource.save(remoteItems.map { it.toEntity(searchQuery) })
            remoteItems.map { it.toModel(searchQuery) }
        } else {
            println("printLn local: $localItems")
            localItems.map { it.toModel(searchQuery) }
        }
    }

    override suspend fun getSearchItemById(etag: String): SearchItem? {
        TODO()
    }

    override suspend fun setVideoAsFavorite(favouriteVideo: SearchItem) {
        println("saving item: $favouriteVideo")
        val mappedToEntityFavouriteVideo = favouriteVideo.toEntity()
        localDataSource.setVideoAsFavorite(mappedToEntityFavouriteVideo)
        Log.d("Test Adapter", "Called setVideoAsFavourite function")
    }


}