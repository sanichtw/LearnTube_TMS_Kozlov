package com.example.learntube.domain.models

import com.example.learntube.data.local.entity.SearchItemEntity

data class SearchItem(
    val snippet: Snippet,
    val etag: String,
    val kindId: Id,
    val searchQuery: String? = "",
)

fun SearchItem.toEntity() = SearchItemEntity(
    snippet = snippet.toEntity(),
    etag = etag,
    kindId = kindId.toEntity(),
    searchQuery = searchQuery
)