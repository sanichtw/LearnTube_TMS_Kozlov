package com.example.learntube.domain.models

import com.example.learntube.data.remote.dto.SearchItemDto


data class SearchItemDomain(
    val snippet: SearchItemDto.Snippet,
    val etag: String,
//    val id: SearchItemDto.Id,
    val kind: String
)

