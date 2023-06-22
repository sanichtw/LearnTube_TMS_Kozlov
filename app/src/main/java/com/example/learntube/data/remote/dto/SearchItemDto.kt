package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.SearchItemEntity
import com.example.learntube.domain.models.SearchItemDomain
import com.google.gson.annotations.SerializedName

data class SearchItemDto(
    @SerializedName("etag")
    val etag: String,

    @SerializedName("id")
    val id: Id,

    @SerializedName("kind")
    val kind: String,

    @SerializedName("snippet")
    val snippet: Snippet
) {
    data class Id(
        @SerializedName("kind")
        val kind: String,

        @SerializedName("playlistId")
        val playlistId: String,

        @SerializedName("videoId")
        val videoId: String
    )

    data class Snippet(
        @SerializedName("channelId")
        val channelId: String,

        @SerializedName("channelTitle")
        val channelTitle: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("liveBroadcastContent")
        val liveBroadcastContent: String,

        @SerializedName("publishTime")
        val publishTime: String,

        @SerializedName("publishedAt")
        val publishedAt: String,

        @SerializedName("thumbnails")
        val thumbnails: ThumbnailsDto,

        @SerializedName("title")
        val title: String
    ) {}
}

fun SearchItemDto.toLocalPost() = SearchItemEntity(
    snippet = snippet,
    etag = etag,
    kind = kind,
)

fun SearchItemDto.toDomainPost() = SearchItemDomain(
    snippet = snippet,
    etag = etag,
    kind = kind,
)