package com.example.learntube.data.repository

import com.example.learntube.data.local.PostEntity
import com.example.learntube.data.remote.dto.PostDto
import com.example.learntube.data.repository.local.PostsLocal
import com.example.learntube.data.repository.remote.PostsRemote
import com.example.learntube.domain.repository.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepositoryImpl @Inject constructor(
    private val postsLocal: PostsLocal,
    private val postsRemote: PostsRemote
): PostsRepository {
    override suspend fun getPostsBySearch(): List<PostDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocalPosts(): List<PostEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getPostById(postId: Long): PostEntity? {
        TODO("Not yet implemented")
    }

}