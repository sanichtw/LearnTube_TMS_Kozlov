package com.example.learntube.data.remote

import com.example.learntube.data.remote.dto.SearchItemDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val searchItemApi: SearchItemApi) {
    suspend fun fetchItems(searchQuery: String?): List<SearchItemDto> =
        searchItemApi.getSearchItems(text = searchQuery).items

    suspend fun fetchSearchItemById(searchItemId: Long): SearchItemDto = TODO()
}