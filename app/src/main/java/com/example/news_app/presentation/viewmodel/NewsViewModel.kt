package com.example.news_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.data.model.NewsItem
import com.example.news_app.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<NewsItem?>>(emptyList())
    val items: StateFlow<List<NewsItem?>> = _items

    private val _selectedItem = MutableStateFlow<NewsItem?>(null)
    val selectedItem: StateFlow<NewsItem?> = _selectedItem



    fun loadItems() {
        viewModelScope.launch {
            try {
                _items.value = repository.getNews()
            } catch (e: Exception) {
                _items.value = emptyList()
            }
        }
    }

    fun loadItemById(id: Int) {

        viewModelScope.launch {
            try {
                val result = async(Dispatchers.IO){repository.getItemById(id)}
                _selectedItem.value = result.await()
            }catch (e: Exception) {
                _selectedItem.value = null
            }


        }
    }
}