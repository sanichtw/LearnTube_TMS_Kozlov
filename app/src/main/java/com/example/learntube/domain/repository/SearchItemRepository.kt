package com.example.learntube.domain.repository

import com.example.learntube.domain.models.SearchItem

interface SearchItemRepository {

    suspend fun getSearchItems(searchQuery: String?): List<SearchItem>

    suspend fun getSearchItemById(searchItemId: Long): SearchItem?
}