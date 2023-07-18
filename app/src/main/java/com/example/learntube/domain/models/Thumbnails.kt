package com.example.learntube.domain.models

import com.example.learntube.data.local.entity.ThumbnailsEntity

//TODO ? в домейне быть не может: значения по умолчанию или пустая модель
internal data class Thumbnails (
    val default: Thumbnail?,
    val high: Thumbnail?,
    val medium: Thumbnail?
)

internal fun Thumbnails.mapToThumbnailsEntity() = ThumbnailsEntity(
    default = default?.mapToThumbnailEntity(),
    high = high?.mapToThumbnailEntity(),
    medium = medium?.mapToThumbnailEntity()
)