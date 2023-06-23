package com.example.learntube.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learntube.domain.models.Thumbnails

@Entity
data class ThumbnailsEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @Embedded(prefix = "default_")
    var default: ThumbnailEntity? = null,

    @Embedded(prefix = "medium_")
    var medium: ThumbnailEntity? = null,

    @Embedded(prefix = "high_")
    var high: ThumbnailEntity? = null
)

fun ThumbnailsEntity.toModel() = Thumbnails(
    default = default?.toModel(),
    high = high?.toModel(),
    medium = medium?.toModel()
)



