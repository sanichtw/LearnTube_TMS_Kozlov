package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.entity.IdEntity
import com.example.learntube.data.local.entity.SearchItemEntity
import com.example.learntube.data.local.entity.SnippetEntity
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.models.Snippet
import com.google.gson.annotations.SerializedName

data class SearchItemDto(
    @SerializedName("etag")
    val etag: String,

    @SerializedName("id")
    val kindId: IdDto,

    @SerializedName("snippet")
    val snippetDto: SnippetDto
)

data class IdDto(
    @SerializedName("kind")
    val kind: String,

    @SerializedName("channelId")
    val channelId: String?,

    @SerializedName("playlistId")
    val playlistId: String?,

    @SerializedName("videoId")
    val videoId: String?
)

data class SnippetDto(
    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("channelId")
    val channelId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("channelTitle")
    val channelTitle: String,

    @SerializedName("thumbnails")
    val thumbnails: ThumbnailsDto,

    @SerializedName("liveBroadcastContent")
    val liveBroadcastContent: String,

    @SerializedName("publishTime")
    val publishTime: String,
)

fun SearchItemDto.toEntity() = SearchItemEntity(
    snippet = snippetDto.toEntity(),
    etag = etag,
    kindId = kindId.toEntity(),
)

fun SearchItemDto.toModel() = SearchItem(
    snippet = snippetDto.toModel(),
    etag = etag,
    kindId = kindId.toModel(),
)

fun SnippetDto.toEntity() = SnippetEntity(
    publishedAt = publishedAt,
    channelId = channelId,
    title = title,
    description = description,
    channelTitle = channelTitle,
    thumbnails = thumbnails.toEntity(),
    liveBroadcastContent = liveBroadcastContent,
    publishTime = publishTime
)

fun SnippetDto.toModel() = Snippet(
    publishedAt = publishedAt,
    channelId = channelId,
    title = title,
    description = description,
    channelTitle = channelTitle,
    thumbnails = thumbnails.toModel(),
    liveBroadcastContent = liveBroadcastContent,
    publishTime = publishTime
)

fun IdDto.toEntity() = IdEntity(
    kind = kind,
    playlistId = playlistId,
    videoId = videoId,
    channelId = channelId
)

fun IdDto.toModel() = com.example.learntube.domain.models.Id(
    kind = kind,
    playlistId = playlistId,
    videoId = videoId,
    channelId = channelId
)