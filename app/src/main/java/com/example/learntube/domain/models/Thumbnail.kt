package com.example.learntube.domain.models

import com.example.learntube.data.local.entity.ThumbnailEntity

internal data class Thumbnail(
    val url: String?,
    val width: Int?,
    val height: Int?
)

internal fun Thumbnail.mapToThumbnailEntity() = ThumbnailEntity(
    url = url,
    width = width,
    height = height
)