package com.example.learntube.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ThumbnailsDto(
    @SerializedName("default")
    val default: Default,
    @SerializedName("high")
    val high: High,
    @SerializedName("medium")
    val medium: Medium
) {
    data class Default(
        @SerializedName("height")
        val height: Int,
        @SerializedName("url")
        val url: String,
        @SerializedName("width")
        val width: Int
    )

    data class High(
        @SerializedName("height")
        val height: Int,
        @SerializedName("url")
        val url: String,
        @SerializedName("width")
        val width: Int
    )

    data class Medium(
        @SerializedName("height")
        val height: Int,
        @SerializedName("url")
        val url: String,
        @SerializedName("width")
        val width: Int
    )
}