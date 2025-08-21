package com.example.data.remote

import com.example.domain.model.NewsItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<NewsItem>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): NewsItem
}