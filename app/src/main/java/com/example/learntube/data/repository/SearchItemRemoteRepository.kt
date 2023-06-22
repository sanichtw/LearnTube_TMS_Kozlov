package com.example.learntube.data.repository

import com.example.learntube.data.remote.dto.SearchItemDto
import com.example.learntube.data.remote.dto.toLocalPost
import com.example.learntube.data.repository.local.SearchItemDao
import com.example.learntube.domain.repository.SearchItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchItemRemoteRepository @Inject constructor(
    private val postsRemote: SearchItemDao
) : SearchItemRepository {
    override suspend fun getSearchItems(search: String?): List<SearchItemDto> {
        val remotePostList = postsLocal.getPosts(search).posts.orEmpty()


        if (remotePostList.isNotEmpty()) {
            remotePostList.forEach { postDto ->
                postsLocal.insertPosts(postEntity = postDto.toLocalPost())
            }
        }

        return remotePostList
    }

    override suspend fun getSearchItemById(searchItemId: Long): SearchItemDto? {
        return postsLocal.getPostById(searchItemId)
    }


}