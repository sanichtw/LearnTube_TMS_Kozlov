package com.example.learntube.domain.use_cases

import com.example.learntube.domain.repository.SearchItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchItemByIdUseCase @Inject constructor(
    private val searchItemRepository: SearchItemRepository
) {
    suspend fun getSearchItemById(etag: String) = searchItemRepository.getSearchItemById(etag)
}