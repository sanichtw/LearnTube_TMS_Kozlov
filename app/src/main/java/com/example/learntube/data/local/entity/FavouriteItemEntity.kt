package com.example.learntube.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteItemEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long = 0,

    @Embedded(prefix = "snippet_")
    var snippet: SnippetEntity,

    @ColumnInfo("etag")
    var etag: String,

    @Embedded(prefix = "kindId_")
    val kindId: IdEntity,

    @ColumnInfo("searchQuery")
    val searchQuery: String?
)