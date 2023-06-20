package com.example.learntube.domain.use_cases

import com.example.learntube.data.remote.dto.toDomainPost
import com.example.learntube.domain.models.DomainPost
import com.example.learntube.domain.repository.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPostsBySearchUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {

    suspend fun getPostsBySearch(): List<DomainPost> = postsRepository.getPostsBySearch().map { postDto ->
        postDto.toDomainPost()
    }
}