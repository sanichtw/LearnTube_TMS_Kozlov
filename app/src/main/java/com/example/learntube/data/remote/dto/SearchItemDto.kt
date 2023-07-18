package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.entity.SearchItemEntity
import com.example.learntube.domain.models.SearchItem
import com.google.gson.annotations.SerializedName

internal data class SearchItemDto(
    @SerializedName("etag")
    val etag: String,

    @SerializedName("id")
    val kindId: IdDto,

    @SerializedName("snippet")
    val snippetDto: SnippetDto
)

internal fun SearchItemDto.mapToSearchItemEntity(searchQuery: String?) = SearchItemEntity(
    snippet = snippetDto.mapToSnippetEntity(),
    etag = etag,
    kindId = kindId.mapToIdEntity(),
    searchQuery = searchQuery
)

internal fun SearchItemDto.mapToSearchItemDomain(searchQuery: String?) = SearchItem(
    snippet = snippetDto.mapToSnippetDomain(),
    etag = etag,
    kindId = kindId.mapToIdDomain(),
    searchQuery = searchQuery
)