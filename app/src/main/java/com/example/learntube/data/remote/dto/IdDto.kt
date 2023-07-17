package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.entity.IdEntity
import com.example.learntube.domain.models.Id
import com.google.gson.annotations.SerializedName

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

internal fun IdDto.toEntity() = IdEntity(
    kind = kind,
    playlistId = playlistId,
    videoId = videoId,
    channelId = channelId
)

internal fun IdDto.toModel() = Id(
    kind = kind,
    playlistId = playlistId,
    videoId = videoId,
    channelId = channelId
)