package com.example.learntube.domain.models

import com.example.learntube.data.local.entity.SearchItemEntity

internal data class SearchItem(
    val snippet: Snippet?,
    val etag: String,
    val kindId: Id?,
    val searchQuery: String?,
)

internal fun SearchItem.mapToSearchItemEntity() = SearchItemEntity(
    snippet = snippet?.mapToSnippetEntity(),
    etag = etag,
    kindId = kindId?.mapToIdEntity(),
    searchQuery = searchQuery
)