package com.example.news_app.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.news_app.presentation.viewmodel.NewsViewModel
import com.example.news_app.utils.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(navController: NavController,itemId: Int,viewModel: NewsViewModel = hiltViewModel()) {
    val item by viewModel.selectedItem.collectAsState()
    viewModel.loadItemById(itemId)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detail Screen") },  navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            })
        }
    ) { padding ->
        item?.let {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                it.title?.let { text -> Text(text = text ,style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,

                    ))}
                Spacer(modifier = Modifier.height(8.dp))
                it.body?.let { text -> Text(text = text,style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Blue

                )) }

                ImageLoaderView(Constants.IMAGE_URL)

            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }


    }
}

@Composable
fun LoadImage(imageUrl: String) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)  // Image URL
            .crossfade(true)
            .build()
    )

    // Image with width, height, and corner radius
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator() // Show loading indicator
        }

        Image(
            painter = painter,
            contentDescription = "Image with rounded corners",
            modifier = Modifier
                .width(50.dp)           // Set width
                .height(50.dp)          // Set height
                .clip(RoundedCornerShape(16.dp)), // Set corner radius (rounded corners)
            // Optional: to take up max width available (if needed)
            contentScale = ContentScale.Crop // Optional: Crop the image to fill the shape
        )
    }
}
