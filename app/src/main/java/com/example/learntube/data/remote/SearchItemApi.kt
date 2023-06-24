package com.example.learntube.data.remote

import com.example.learntube.data.remote.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchItemApi {
    companion object {
        const val GET_POSTS_REQUEST = "search"

        const val PART_PARAM = "part"
        const val PART_CONST = "snippet"

        const val QUERY_PARAM = "q"
        const val QUERY_CONST = "Javascript Courses"

        const val QUERY_RESULT = "maxResults"
        const val QUERY_RESULT_CONST = 50

        const val KEY_PARAM = "key"
        const val API_KEY = "AIzaSyCUMVXuscyGvyEzOJuTPPCOFNQunaDsWIA"
    }

    @GET(GET_POSTS_REQUEST)
    suspend fun getSearchItems(
        @Query(PART_PARAM) part: String = PART_CONST,
        @Query(QUERY_RESULT) maxResults: Int = QUERY_RESULT_CONST,
        @Query(QUERY_PARAM) text: String? = QUERY_CONST,
        @Query(KEY_PARAM) apiKey: String = API_KEY
    ): ResponseDto
}