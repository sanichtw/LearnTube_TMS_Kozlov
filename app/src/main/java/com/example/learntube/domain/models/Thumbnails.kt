package com.example.learntube.domain.models

import com.example.learntube.data.local.entity.ThumbnailsEntity

internal data class Thumbnails(
    val defaultSize: Thumbnail?,
    val mediumSize: Thumbnail?,
    val highSize: Thumbnail?,
)

internal fun Thumbnails.mapToThumbnailsEntity() = ThumbnailsEntity(
    defaultSize = defaultSize?.mapToThumbnailEntity(),
    mediumSize = mediumSize?.mapToThumbnailEntity(),
    highSize = highSize?.mapToThumbnailEntity(),
)