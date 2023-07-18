package com.example.learntube.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.learntube.domain.models.Id

//TODO internal
@Entity
internal class IdEntity(
    @ColumnInfo("item_kind")
    var kind: String?,

    @ColumnInfo("playlistId")
    var playlistId: String?,

    @ColumnInfo("videoId")
    var videoId: String?,

    @ColumnInfo("channelId")
    var channelId: String?
)

internal fun IdEntity.mapToIdDomain() = Id(
    kind = kind,
    playlistId = playlistId,
    videoId = videoId,
    channelId = channelId
)

