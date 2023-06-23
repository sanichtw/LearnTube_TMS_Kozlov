package com.example.learntube.data.repository

import com.example.learntube.data.local.LocalDataSource
import com.example.learntube.data.local.entity.SearchItemEntity
import com.example.learntube.data.local.entity.toModel
import com.example.learntube.data.remote.RemoteDataSource
import com.example.learntube.data.remote.dto.toEntity
import com.example.learntube.data.remote.dto.toModel
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.repository.SearchItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchItemRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): SearchItemRepository {
    override suspend fun getSearchItems(searchQuery: String?): List<SearchItem> {
        val localItems: List<SearchItemEntity> = searchQuery?.let {
            localDataSource.getAll(searchQuery)
        } ?: emptyList()

        return if (localItems.isEmpty()) {
            val remoteItems = remoteDataSource.fetchItems()
            localDataSource.save(remoteItems.map { it.toEntity() })
            remoteItems.map { it.toModel() }
        } else {
            localItems.map {it.toModel() }
        }
    }

    override suspend fun getSearchItemById(searchItemId: Long): SearchItem? {
        val localItem = localDataSource.getById(searchItemId)

        return localItem?.toModel() ?: remoteDataSource.fetchSearchItemById(searchItemId)?.let {
            val itemDto = it
            localDataSource.save(itemDto.toEntity())
            itemDto.toModel()
        }
    }
}