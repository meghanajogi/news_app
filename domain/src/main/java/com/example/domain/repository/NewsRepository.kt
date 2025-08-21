package com.example.domain.repository

import com.example.domain.model.NewsItem
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow


interface  NewsRepository{
    fun getItems(): Flow<Resource<List<NewsItem>>>
    fun getItemById(id: Int):  Flow<Resource<NewsItem>>
}

/*
@Singleton
class NewsRepository  @Inject constructor(private val apiService: ApiService) {
  fun getNews(): Flow<Resource<List<NewsItem>>> = flow {
      emit(Resource.Loading())
      try {
          val response = apiService.getPosts()
          emit(Resource.Success(response))
      } catch (e: Exception) {
          emit(Resource.Error("Failed to load news: ${e.localizedMessage ?: "Unknown error"}"))
      }
  }.flowOn(Dispatchers.IO)

    fun getItemById(id: Int): Flow<Resource<NewsItem>> = flow {
        emit(Resource.Loading())
        try {
            val response =  apiService.getPostById(id)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to load news: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }.flowOn(Dispatchers.IO)
}*/
