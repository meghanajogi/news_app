package com.example.news_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    private val _selectedItem = MutableStateFlow<Item?>(null)
    val selectedItem: StateFlow<Item?> = _selectedItem



    fun loadItems() {
        viewModelScope.launch {
            try {
                _items.value = repository.getUsers()
            } catch (e: Exception) {
                _items.value = emptyList()
            }
        }
    }

    fun loadItemById(id: Int) {
        viewModelScope.launch {
            try {
                _selectedItem.value = repository.getItemById(id)
            }catch (e: Exception) {
                _selectedItem.value = null
            }


        }
    }
}