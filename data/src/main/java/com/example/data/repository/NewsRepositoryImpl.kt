package com.example.data.repository

import com.example.data.remote.ApiService
import com.example.domain.model.NewsItem
import com.example.domain.repository.NewsRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepositoryImpl @Inject constructor(private val apiService: ApiService): NewsRepository {
   override fun getItems(): Flow<Resource<List<NewsItem>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getPosts()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to load news: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }.flowOn(Dispatchers.IO)

   override fun getItemById(id: Int): Flow<Resource<NewsItem>> = flow {
        emit(Resource.Loading())
        try {
            val response =  apiService.getPostById(id)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to load news: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }.flowOn(Dispatchers.IO)
}