package com.example.news_app.data.repository

import com.example.news_app.data.model.NewsItem
import com.example.news_app.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository  @Inject constructor(private val apiService: ApiService) {


    suspend fun getNews(): List<NewsItem> {
        val result=apiService.getPosts()
        return result
    }

    suspend fun getItemById(id:Int): NewsItem {
        val result=apiService.getPostById(id)
        return result
    }
}