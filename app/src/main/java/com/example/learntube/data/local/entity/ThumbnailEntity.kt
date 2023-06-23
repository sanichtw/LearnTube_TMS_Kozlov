package com.example.learntube.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learntube.domain.models.Thumbnail
import com.example.learntube.domain.models.Thumbnails
import com.google.gson.annotations.SerializedName

@Entity
data class ThumbnailEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long = 0,

    @ColumnInfo("url")
    var url: String,

    @ColumnInfo("width")
    var width: Int,

    @ColumnInfo("height")
    var height: Int
)

fun ThumbnailEntity.toModel() = Thumbnail(
    url = url,
    width = width,
    height = height
)