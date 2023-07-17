package com.example.learntube.domain.models

import com.example.learntube.data.local.entity.SnippetEntity

data class Snippet(
    val publishedAt: String = "",

    val channelId: String = "",

    val title: String = "",

    val description: String = "",

    val channelTitle: String = "",

    val thumbnails: Thumbnails,

    val liveBroadcastContent: String = "",

    val publishTime: String = "",

    var isFavourite: Boolean = false
)

fun Snippet.toEntity() = SnippetEntity(
    publishedAt = publishedAt,
    channelId = channelId,
    title = title,
    description = description,
    channelTitle = channelTitle,
    thumbnails = thumbnails.toEntity(),
    liveBroadcastContent = liveBroadcastContent,
    publishTime = publishTime,
    isFavourite = isFavourite
)