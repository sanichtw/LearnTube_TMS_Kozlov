package com.example.learntube.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto (
//    @SerializedName("pageInfo")
//    val pageInfo: Int,

    @SerializedName("items")
    val posts: List<PostDto>?)