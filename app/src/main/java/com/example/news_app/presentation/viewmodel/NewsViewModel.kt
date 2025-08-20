package com.example.news_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.data.model.NewsItem
import com.example.news_app.data.repository.NewsRepository
import com.example.news_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _items = MutableStateFlow<Resource<List<NewsItem?>>>(Resource.Loading())
    val items: StateFlow<Resource<List<NewsItem?>>> = _items

    private val _selectedItem = MutableStateFlow<Resource<NewsItem?>>(Resource.Loading())
    val selectedItem: StateFlow<Resource<NewsItem?>> = _selectedItem



    fun loadItems() {
        viewModelScope.launch {
            try {
                repository.getNews().collect { list->
                    _items.value=list
                }
            } catch (e: Exception) {
                _items.value = Resource.Error("Failed to load news: ${e.localizedMessage ?: "Unknown error"}")
            }
        }
    }

    fun loadItemById(id: Int) {

        viewModelScope.launch {
            try {
             repository.getItemById(id).collect { item->
                    _selectedItem.value = item
                }
            }catch (e: Exception) {
                _selectedItem.value = Resource.Error("Failed to load news: ${e.localizedMessage ?: "Unknown error"}")
            }

        }
    }
}