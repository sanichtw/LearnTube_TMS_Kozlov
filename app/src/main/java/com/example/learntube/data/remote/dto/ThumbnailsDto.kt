package com.example.learntube.data.remote.dto

import com.example.learntube.data.local.entity.ThumbnailsEntity
import com.example.learntube.domain.models.Thumbnails
import com.google.gson.annotations.SerializedName

internal data class ThumbnailsDto(
    @SerializedName("default")
    val defaultSize: ThumbnailDto?,

    @SerializedName("high")
    val highSize: ThumbnailDto?,

    @SerializedName("medium")
    val mediumSize: ThumbnailDto?
)

internal fun ThumbnailsDto.mapToThumbnailsEntity() = ThumbnailsEntity(
    defaultSize = defaultSize?.mapToThumbnailEntity(),
    mediumSize = mediumSize?.mapToThumbnailEntity(),
    highSize = highSize?.mapToThumbnailEntity(),
)

internal fun ThumbnailsDto.mapToThumbnailsDomain() = Thumbnails(
    defaultSize = defaultSize?.mapToThumbnailDomain(),
    mediumSize = mediumSize?.mapToThumbnailDomain(),
    highSize = highSize?.mapToThumbnailDomain()
)

