package com.example.learntube.domain.models

import com.google.gson.annotations.SerializedName

data class Thumbnail (
    val url: String,

    val width: Int,

    val height: Int
)