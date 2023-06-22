package com.example.learntube.domain.use_cases

import com.example.learntube.domain.repository.SearchItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPostInfoUseCase @Inject constructor(
    private val searchItemRepository: SearchItemRepository
) {

    suspend fun getPostInfo(postId: Long) = searchItemRepository.getSearchItemById(searchItemId = postId)
}