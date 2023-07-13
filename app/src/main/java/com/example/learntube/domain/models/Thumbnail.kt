package com.example.learntube.domain.models

import com.example.learntube.data.local.entity.ThumbnailEntity

data class Thumbnail(
    val url: String,

    val width: Int,

    val height: Int
)

fun Thumbnail.toEntity() = ThumbnailEntity(
    url = url,
    width = width,
    height = height
)