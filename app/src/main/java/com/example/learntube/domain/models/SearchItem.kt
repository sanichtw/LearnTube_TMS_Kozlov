package com.example.learntube.domain.models

data class SearchItem(
    val snippet: Snippet,
    val etag: String,
    val kindId: Id
)

