package com.example.news_app.network

import com.example.news_app.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getUsers(): List<Item>
}