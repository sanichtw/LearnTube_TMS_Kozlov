package com.example.learntube.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learntube.domain.models.Snippet

@Entity
internal data class SnippetEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long = 0,

    @ColumnInfo("publishedAt")
    var publishedAt: String?,

    @ColumnInfo("channelId")
    var channelId: String?,

    @ColumnInfo("title")
    var title: String?,

    @ColumnInfo("description")
    var description: String?,

    @ColumnInfo("channelTitle")
    var channelTitle: String?,

    @Embedded(prefix = "thumbnails_")
    var thumbnails: ThumbnailsEntity?,

    @ColumnInfo("liveBroadcastContent")
    var liveBroadcastContent: String?,

    @ColumnInfo("publishTime")
    var publishTime: String?,

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean = false
) {
    companion object {
        fun empty(): SnippetEntity = SnippetEntity(
            id = 0,
            publishedAt = "",
            channelId = "",
            title = "",
            description = "",
            channelTitle = "",
            thumbnails = ThumbnailsEntity.empty(),
            liveBroadcastContent = "",
            publishTime = "",
            isFavourite = false
        )
    }
}

//TODO rename toSnippetModel()
internal fun SnippetEntity.mapToSnippetDomain() = Snippet(
    publishedAt = publishedAt,
    channelId = channelId,
    title = title,
    description = description,
    channelTitle = channelTitle,
    thumbnails = thumbnails?.mapToThumbnailsDomain(),
    liveBroadcastContent = liveBroadcastContent,
    publishTime = publishTime,
    isFavourite = isFavourite
)