package com.example.learntube.data.repository.remote

import com.example.learntube.data.remote.api.SearchItemApi
import com.example.learntube.data.remote.dto.ResponseDto
import javax.inject.Inject

class PostsRemoteImpl @Inject constructor(
    private val searchItemApi: SearchItemApi
) : PostsRemote {
    override suspend fun getPosts(): ResponseDto = searchItemApi.getSearchItems()
}