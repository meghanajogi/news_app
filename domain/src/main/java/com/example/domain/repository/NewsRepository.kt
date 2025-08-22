package com.example.domain.repository

import com.example.domain.model.NewsItem
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface  NewsRepository{
    fun getItems(): Flow<Resource<List<NewsItem>>>
    fun getItemById(id: Int):  Flow<Resource<NewsItem>>
}

