package com.example.learntube.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learntube.domain.models.SearchItem

@Entity
internal class SearchItemEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo("etag")
    var etag: String = "",

    @Embedded(prefix = "snippet_")
    var snippet: SnippetEntity? = SnippetEntity.empty(),

    @Embedded(prefix = "kindId_")
    val kindId: IdEntity? = IdEntity.empty(),

    @ColumnInfo("searchQuery")
    var searchQuery: String? = ""
)

internal fun SearchItemEntity.mapToSearchItemDomain(searchQuery: String?) = SearchItem(
    snippet = snippet?.mapToSnippetDomain(),
    etag = etag,
    kindId = kindId?.mapToIdDomain(),
    searchQuery = searchQuery
)

internal fun SearchItemEntity.mapToSearchItemDomain() = SearchItem(
    snippet = snippet?.mapToSnippetDomain(),
    etag = etag,
    kindId = kindId?.mapToIdDomain(),
    searchQuery = searchQuery
)
