package com.example.learntube.data.remote.dto

import com.google.gson.annotations.SerializedName

//TODO ? and remove unused
internal data class ResponseDto(
    @SerializedName("kind")
    val kind: String,

    @SerializedName("etag")
    val etag: String,

    @SerializedName("nextPageToken")
    val nextPageToken: String,

    @SerializedName("regionCode")
    val regionCode: String,

    @SerializedName("pageInfo")
    val pageInfo: PageInfoDto,

    @SerializedName("items")
    val items: List<SearchItemDto>
)