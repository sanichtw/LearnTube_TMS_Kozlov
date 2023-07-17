package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.entity.SnippetEntity
import com.example.learntube.domain.models.Snippet
import com.google.gson.annotations.SerializedName

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

internal fun SnippetDto.toEntity() = SnippetEntity(
    publishedAt = publishedAt,
    channelId = channelId,
    title = title,
    description = description,
    channelTitle = channelTitle,
    thumbnails = thumbnails.toEntity(),
    liveBroadcastContent = liveBroadcastContent,
    publishTime = publishTime
)

internal fun SnippetDto.toModel() = Snippet(
    publishedAt = publishedAt,
    channelId = channelId,
    title = title,
    description = description,
    channelTitle = channelTitle,
    thumbnails = thumbnails.toModel(),
    liveBroadcastContent = liveBroadcastContent,
    publishTime = publishTime
)