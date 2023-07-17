package com.example.learntube.domain.models

import com.example.learntube.data.local.entity.IdEntity

data class Id(
    var kind: String? = "",
    var playlistId: String? = "",
    var videoId: String? = "",
    var channelId: String? = "",
)

fun Id.toEntity() = IdEntity(
    kind = kind,
    playlistId = playlistId,
    videoId = videoId,
    channelId = channelId
)
