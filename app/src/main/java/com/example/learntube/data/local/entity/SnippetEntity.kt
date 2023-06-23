package com.example.learntube.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learntube.data.remote.dto.ThumbnailsDto
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.domain.models.Snippet
import com.google.gson.annotations.SerializedName

@Entity
data class SnippetEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long = 0,

    @ColumnInfo("publishedAt")
    var publishedAt: String,

    @ColumnInfo("channelId")
    var channelId: String,

    @ColumnInfo("title")
    var title: String,

    @ColumnInfo("description")
    var description: String,

    @ColumnInfo("channelTitle")
    var channelTitle: String,

    @Embedded(prefix = "thumbnails_")
    var thumbnails: ThumbnailsEntity,

    @ColumnInfo("liveBroadcastContent")
    var liveBroadcastContent: String,

    @ColumnInfo("publishTime")
    var publishTime: String,
)

fun SnippetEntity.toModel() = Snippet(
    publishedAt = publishedAt,
    channelId = channelId,
    title = title,
    description = description,
    channelTitle = channelTitle,
    thumbnails = thumbnails.toModel(),
    liveBroadcastContent = liveBroadcastContent,
    publishTime = publishTime,
)