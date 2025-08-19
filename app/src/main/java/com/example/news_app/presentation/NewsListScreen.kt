package com.example.news_app.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


data class Item(val id: Int, val title: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  NewsListScreen(navController: NavController){
    val items = List(10) { Item(it, "Item #$it") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("List Screen") })
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { item ->
                ListItem(item = item) {
                    navController.navigate("detail/${item.id}")
                }

            }
        }
    }

}

@Composable
fun ListItem(item: Item, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.title, )
            Text(text = "Click to view details")
        }
    }
}