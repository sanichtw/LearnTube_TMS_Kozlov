package com.example.learntube.domain.models

import androidx.room.ColumnInfo
import com.example.learntube.data.local.entity.IdEntity

data class Id(
    var kind: String,
    var playlistId: String? = null,
    var videoId: String? = null,
    var channelId: String? = null,
)

fun Id.toEntity() = IdEntity(
    kind = kind,
    playlistId = playlistId,
    videoId = videoId,
    channelId = channelId
)
