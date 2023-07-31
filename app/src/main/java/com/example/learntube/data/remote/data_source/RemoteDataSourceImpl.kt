package com.example.learntube.data.remote.data_source

import com.example.learntube.data.remote.SearchItemApi
import com.example.learntube.data.remote.dto.SearchItemDto
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(
    private val searchItemApi: SearchItemApi
) : RemoteDataSource {
    override suspend fun fetchItems(searchQuery: String?): List<SearchItemDto>? =
        searchItemApi.getSearchItems(text = searchQuery).items
}