package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.entity.ThumbnailEntity
import com.example.learntube.domain.models.Thumbnail
import com.google.gson.annotations.SerializedName

data class ThumbnailDto(
    @SerializedName("url")
    val url: String,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int
)

internal fun ThumbnailDto.toEntity() = ThumbnailEntity(
    url = url,
    width = width,
    height = height
)

internal fun ThumbnailDto.toModel() = Thumbnail(
    url = url,
    width = width,
    height = height
)