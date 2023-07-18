package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.entity.ThumbnailsEntity
import com.example.learntube.domain.models.Thumbnails
import com.google.gson.annotations.SerializedName

internal data class ThumbnailsDto(
    @SerializedName("default")
    val default: ThumbnailDto,

    @SerializedName("high")
    val high: ThumbnailDto,

    @SerializedName("medium")
    val medium: ThumbnailDto
)

internal fun ThumbnailsDto.mapToThumbnailsEntity() = ThumbnailsEntity(
    default = default.mapToThumbnailEntity(),
    high = high.mapToThumbnailEntity(),
    medium = medium.mapToThumbnailEntity()
)

internal fun ThumbnailsDto.mapToThumbnailsDomain() = Thumbnails(
    default = default.mapToThumbnailDomain(),
    high = high.mapToThumbnailDomain(),
    medium = medium.mapToThumbnailDomain()
)

