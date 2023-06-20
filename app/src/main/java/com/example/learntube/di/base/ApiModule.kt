package com.example.learntube.di.base

import com.example.learntube.data.remote.api.PostsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    //    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val BASE_URL = "https://youtube.googleapis.com/youtube/v3"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create()) //add json converter
            .baseUrl(BASE_URL) //base url
            .client(okHttpClient) //okHttp client
            .build()

    @Singleton
    @Provides
    fun providePostApiService(retrofit: Retrofit): PostsApi = retrofit.create(PostsApi::class.java)
}