package com.example.learntube.domain.use_cases

import com.example.learntube.domain.repository.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPostInfoUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {

    suspend fun getPostInfo(postId: Long) = postsRepository.getPostById(postId = postId)
}