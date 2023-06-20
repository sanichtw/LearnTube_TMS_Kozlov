package com.example.learntube.data.remote.dto

import com.example.learntube.domain.models.DomainPost
import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("urlToImage")
    val urlToImage: String,

    @SerializedName("publishedAt")
    val publishedAt: String
)

fun PostDto.toDomainPost() = DomainPost(
    title = title
)

fun PostDto.toLocalPost() = DomainPost(
    title = title
)