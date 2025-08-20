package com.example.news_app.data.repository

import com.example.news_app.data.model.NewsItem
import com.example.news_app.network.ApiService
import com.example.news_app.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository  @Inject constructor(private val apiService: ApiService) {
  fun getNews(): Flow<Resource<List<NewsItem?>>> = flow {
      emit(Resource.Loading())
      try {
          val response = apiService.getPosts()
          emit(Resource.Success(response))
      } catch (e: Exception) {
          emit(Resource.Error("Failed to load news: ${e.localizedMessage ?: "Unknown error"}"))
      }
  }

    fun getItemById(id: Int): Flow<Resource<NewsItem?>> = flow {
        emit(Resource.Loading())
        try {
            val response =  apiService.getPostById(id)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to load news: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }
}