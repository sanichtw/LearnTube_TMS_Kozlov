package com.example.learntube.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PageInfoDto(
    @SerializedName("resultsPerPage")
    val resultsPerPage: Int?,

    @SerializedName("totalResults")
    val totalResults: Int?
)