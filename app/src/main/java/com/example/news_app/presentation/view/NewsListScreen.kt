package com.example.news_app.presentation.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.news_app.data.model.Item
import com.example.news_app.presentation.viewmodel.NewsViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(navController: NavController, viewModel: NewsViewModel = hiltViewModel()){
    val items by viewModel.items.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadItems() // Load items when the screen is displayed
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("List Screen") })
        }
    ) { padding ->

       if(!items.isEmpty()){
            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(items) { item ->
                    ListItem(item = item) {
                        navController.navigate("detail/${item?.id}")
                    }

                }
            }
        } else{
           Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
               CircularProgressIndicator()
           }

       }
    }

}

@Composable
fun ListItem(item: Item?, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            ImageLoaderView("https://techcrunch.com/wp-content/uploads/2024/05/Minecraft-keyart.jpg?resize=1200,720")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item?.title?:"",  style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,

            )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item?.body?:"",  style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Blue

            ))
        }
    }
}

@Composable
fun LoadImage(imageUrl: String) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build()
    )
    Log.d("The state is ${painter.state}","new")
    when ( painter.state) {

        is AsyncImagePainter.State.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is AsyncImagePainter.State.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error loading image", color = Color.Red)
            }
        }
        else -> {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}




