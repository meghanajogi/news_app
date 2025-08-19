package com.example.news_app

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository  @Inject constructor() {
    fun getItems(): List<Item> = List(10) {
        Item(it, "Item #$it", "Description for item #$it")
    }

    fun getItemById(id: Int): Item = Item(id, "Item #$id", "Detailed description for item #$id")
}
