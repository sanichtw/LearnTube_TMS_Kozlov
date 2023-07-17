package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.entity.SearchItemEntity
import com.example.learntube.domain.models.SearchItem
import com.google.gson.annotations.SerializedName

data class SearchItemDto(
    @SerializedName("etag")
    val etag: String,

    @SerializedName("id")
    val kindId: IdDto,

    @SerializedName("snippet")
    val snippetDto: SnippetDto
)

internal fun SearchItemDto.toEntity(searchQuery: String?) = SearchItemEntity(
    snippet = snippetDto.toEntity(),
    etag = etag,
    kindId = kindId.toEntity(),
    searchQuery = searchQuery
)

internal fun SearchItemDto.toModel(searchQuery: String?) = SearchItem(
    snippet = snippetDto.toModel(),
    etag = etag,
    kindId = kindId.toModel(),
    searchQuery = searchQuery
)