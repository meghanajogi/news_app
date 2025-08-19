package com.example.news_app

import com.example.news_app.model.Item
import com.example.news_app.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository  @Inject constructor(private val apiService: ApiService) {

    fun getItemById(id: Int): Item = Item(id, "Item #$id", "Detailed description for item #$id","news")

    suspend fun getUsers(): List<Item> {
        val result=apiService.getUsers()
        return result
    }
}
