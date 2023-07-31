package com.example.learntube.data.remote.dto

import com.google.gson.annotations.SerializedName

internal data class YoutubeApiResponse(
    @SerializedName("kind")
    val kind: String?,

    @SerializedName("etag")
    val etag: String?,

    @SerializedName("nextPageToken")
    val nextPageToken: String?,

    @SerializedName("pageInfo")
    val pageInfo: PageInfoDto?,

    @SerializedName("items")
    val items: List<SearchItemDto>?
)