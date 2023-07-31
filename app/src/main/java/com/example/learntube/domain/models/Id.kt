package com.example.learntube.domain.models

import com.example.learntube.data.local.entity.IdEntity

internal class Id(
    var kind: String?,
    var playlistId: String?,
    var videoId: String?,
    var channelId: String?,
)

internal fun Id.mapToIdEntity() = IdEntity(
    kind = kind,
    playlistId = playlistId,
    videoId = videoId,
    channelId = channelId
)
