package com.example.learntube.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("items")
    val posts: List<SearchItemDto>?,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("nextPageToken")
    val nextPageToken: String,
    @SerializedName("pageInfo")
    val pageInfo: PageInfoDto,
    @SerializedName("regionCode")
    val regionCode: String
) {}