package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.entity.ThumbnailEntity
import com.example.learntube.data.local.entity.ThumbnailsEntity
import com.example.learntube.data.local.entity.toModel
import com.example.learntube.domain.models.Thumbnail
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

data class ThumbnailDto(
    @SerializedName("url")
    val url: String,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int
)

fun ThumbnailsDto.toEntity() = ThumbnailsEntity(
    default = default.toEntity(),
    high = high.toEntity(),
    medium = medium.toEntity()
)

fun ThumbnailDto.toEntity() = ThumbnailEntity(
    url = url,
    width = width,
    height = height
)

fun ThumbnailsDto.toModel() = Thumbnails(
    default = default.toModel(),
    high = high.toModel(),
    medium = medium.toModel()
)

fun ThumbnailDto.toModel() = Thumbnail(
    url = url,
    width = width,
    height = height
)