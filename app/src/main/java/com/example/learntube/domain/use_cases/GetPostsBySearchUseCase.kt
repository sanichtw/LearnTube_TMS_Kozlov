package com.example.learntube.domain.use_cases

import com.example.learntube.data.remote.dto.toDomainPost
import com.example.learntube.domain.models.SearchItemDomain
import com.example.learntube.domain.repository.SearchItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPostsBySearchUseCase @Inject constructor(
    private val searchItemRepository: SearchItemRepository
) {

    suspend fun getPostsBySearch(search: String? = null): List<SearchItemDomain> = searchItemRepository.getSearchItems(search).map { postDto ->
        postDto.toDomainPost()
    }
}