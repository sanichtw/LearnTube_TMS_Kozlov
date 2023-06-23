package com.example.learntube.domain.use_cases

import com.example.learntube.data.repository.SearchItemRepositoryImpl
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.repository.SearchItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchItemsBySearchQueryUseCase @Inject constructor(
    private val searchItemRepository: SearchItemRepository
) {
    suspend fun getSearchItemsBySearchQuery(searchQuery: String? = null): List<SearchItem> = searchItemRepository.getSearchItems(searchQuery)

}