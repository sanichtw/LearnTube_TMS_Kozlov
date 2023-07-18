package com.example.learntube.data.remote.data_source

import com.example.learntube.data.remote.dto.SearchItemDto

internal interface RemoteDataSource {
    suspend fun fetchItems(searchQuery: String?): List<SearchItemDto>
}