package com.example.news_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.data.model.NewsItem
import com.example.news_app.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
                repository.getNews().collect { list->
                    _items.value=list
                }
            } catch (e: Exception) {
                _items.value = emptyList()
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
                _selectedItem.value = null
            }

        }
    }
}