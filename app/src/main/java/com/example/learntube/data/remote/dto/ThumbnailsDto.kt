package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.entity.ThumbnailsEntity
import com.example.learntube.domain.models.Thumbnails
import com.google.gson.annotations.SerializedName

data class ThumbnailsDto(
    @SerializedName("default")
    val default: ThumbnailDto,

    @SerializedName("high")
    val high: ThumbnailDto,

    @SerializedName("medium")
    val medium: ThumbnailDto
)

internal fun ThumbnailsDto.toEntity() = ThumbnailsEntity(
    default = default.toEntity(),
    high = high.toEntity(),
    medium = medium.toEntity()
)

internal fun ThumbnailsDto.toModel() = Thumbnails(
    default = default.toModel(),
    high = high.toModel(),
    medium = medium.toModel()
)

