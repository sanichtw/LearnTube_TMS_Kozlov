package com.example.learntube.domain.repository

import com.example.learntube.data.local.PostEntity
import com.example.learntube.data.remote.dto.PostDto


interface PostsRepository{
    suspend fun getPostsBySearch(): List<PostDto>
    suspend fun getLocalPosts(): List<PostEntity>
    suspend fun getPostById(postId: Long): PostEntity?
}