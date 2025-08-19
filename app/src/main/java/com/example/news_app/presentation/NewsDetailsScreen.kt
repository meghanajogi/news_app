package com.example.news_app.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news_app.NewsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(itemId: Int,viewModel: NewsViewModel = hiltViewModel()) {

    LaunchedEffect(itemId) {
        viewModel.loadItemById(itemId)
    }
    val item by viewModel.selectedItem.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detail Screen") })
        }
    ) { padding ->
        item?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(text = it.title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.description)
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }


    }
}
