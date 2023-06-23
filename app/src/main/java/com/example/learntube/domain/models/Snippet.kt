package com.example.learntube.domain.models

import androidx.room.PrimaryKey
import com.example.learntube.data.remote.dto.ThumbnailsDto

data class Snippet (
    val publishedAt: String,

    val channelId: String,

    val title: String,

    val description: String,

    val channelTitle: String,

    val thumbnails: Thumbnails,

    val liveBroadcastContent: String,

    val publishTime: String,
)