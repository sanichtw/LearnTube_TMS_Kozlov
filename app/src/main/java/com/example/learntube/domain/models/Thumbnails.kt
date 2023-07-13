package com.example.learntube.domain.models

import com.example.learntube.data.local.entity.ThumbnailsEntity

data class Thumbnails (
    val default: Thumbnail?,

    val high: Thumbnail?,

    val medium: Thumbnail?
)

fun Thumbnails.toEntity() = ThumbnailsEntity(
    default = default?.toEntity(),
    high = high?.toEntity(),
    medium = medium?.toEntity()
)