package com.example.learntube.data.repository.remote

import com.example.learntube.data.remote.dto.ResponseDto

interface PostsRemote {
    suspend fun getPosts(): ResponseDto
}