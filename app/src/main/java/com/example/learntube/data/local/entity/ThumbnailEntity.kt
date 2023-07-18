package com.example.learntube.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.learntube.domain.models.Thumbnail

@Entity
data class ThumbnailEntity(
    @ColumnInfo("url")
    var url: String? = "",

    @ColumnInfo("width")
    var width: Int? = 0,

    @ColumnInfo("height")
    var height: Int? = 0
)

internal fun ThumbnailEntity.mapToThumbnailDomain() = Thumbnail(
    url = url,
    width = width,
    height = height
)