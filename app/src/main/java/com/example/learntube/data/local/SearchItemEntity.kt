package com.example.learntube.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learntube.data.remote.dto.SearchItemDto

@Entity
data class SearchItemEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @Embedded
    var snippet: SearchItemDto.Snippet? = null,

    @ColumnInfo("etag")
    val etag: String? = null,

//    @ColumnInfo("id")
//    val id: SearchItemDto.Id? = null,

    @ColumnInfo("kind")
    val kind: String? = null,
)
