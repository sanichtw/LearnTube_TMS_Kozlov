package com.example.learntube.data.remote.api

import com.example.learntube.data.remote.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {
    companion object {

        //        const val GET_POSTS_REQUEST = "top-headlines"
        const val GET_POSTS_REQUEST = "search"

        //        const val СOUNTRY_PARAM = "country"
        const val PART_PARAM = "part"

        //        const val СOUNTRY_CONST = "us"
        const val PART_CONST = "snippet"

        //        const val CATEGORY_PARAM = "category"
        const val QUERY_PARAM = "q"
        //        const val CATEGORY_CONST = "business"
        const val QUERY_CONST = "Android Courses"

        //        const val KEY_PARAM = "apiKey"
        const val KEY_PARAM = "key"
        //        const val API_KEY = "ba5158e25ef645068d371293f5d3c646"
        const val API_KEY = "AIzaSyCUMVXuscyGvyEzOJuTPPCOFNQunaDsWIA"

    }

    @GET(GET_POSTS_REQUEST)
    suspend fun getLatestNews(
        @Query(PART_PARAM) country: String = PART_CONST,
        @Query(QUERY_PARAM) category: String = QUERY_CONST,
        @Query(KEY_PARAM) apiKey: String = API_KEY
    ): ResponseDto
}