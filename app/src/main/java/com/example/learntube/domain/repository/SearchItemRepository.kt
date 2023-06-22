package com.example.learntube.domain.repository

import com.example.learntube.data.remote.dto.SearchItemDto


interface SearchItemRepository{
    suspend fun getSearchItems(search: String?): List<SearchItemDto>
    suspend fun getSearchItemById(searchItemId: Long): SearchItemDto?
}