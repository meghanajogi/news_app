package com.example.news_app.data.repository

import com.example.news_app.data.model.NewsItem
import com.example.news_app.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository  @Inject constructor(private val apiService: ApiService) {
  fun getNews(): Flow<List<NewsItem>> = flow {
      val result = apiService.getPosts()
      emit(result)
  }

    fun getItemById(id: Int): Flow<NewsItem> = flow {
        val result = apiService.getPostById(id)
        emit(result)
    }
}