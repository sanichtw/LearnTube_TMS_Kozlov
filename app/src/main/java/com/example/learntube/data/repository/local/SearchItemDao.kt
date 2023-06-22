package com.example.learntube.data.repository.local

import com.example.learntube.data.local.SearchItemEntity
import com.example.learntube.domain.models.SearchItemDomain

interface SearchItemDao {
    suspend fun getSearchItems(search: String?): List<SearchItemDomain>?
    suspend fun getById(searchItemId: Long): SearchItemDomain?
}