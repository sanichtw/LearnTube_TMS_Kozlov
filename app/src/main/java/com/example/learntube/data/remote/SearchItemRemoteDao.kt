package com.example.learntube.data.remote

import com.example.learntube.data.local.SearchItemEntity
import com.example.learntube.data.remote.api.SearchItemApi
import com.example.learntube.data.remote.dto.ResponseDto
import com.example.learntube.data.repository.local.SearchItemDao
import javax.inject.Inject

class SearchItemRemoteDao @Inject constructor(
    private val searchItemApi: SearchItemApi
) :  SearchItemDao {
    override suspend fun getSearchItems(search: String?): List<ResponseDto>? {
        val searchItems: ResponseDto = searchItemApi.getSearchItems()
        TODO("map to SearchItemDomain and return")
    }

    override suspend fun getById(searchItemId: Long): SearchItemEntity? {
        TODO("Not yet implemented")
    }

}