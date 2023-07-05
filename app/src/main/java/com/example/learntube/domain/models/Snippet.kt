package com.example.learntube.domain.models

data class Snippet(
    val publishedAt: String,

    val channelId: String,

    val title: String,

    val description: String,

    val channelTitle: String,

    val thumbnails: Thumbnails,

    val liveBroadcastContent: String,

    val publishTime: String,
)