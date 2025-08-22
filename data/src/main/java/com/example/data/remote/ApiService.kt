package com.example.data.remote

import com.example.data.remote.dto.NewsItemDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<NewsItemDto>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): NewsItemDto
}