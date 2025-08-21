package com.example.news_app.uii.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.domain.utils.Constants

@Composable
fun ImageLoaderView(modifier: Modifier = Modifier) {
    // Coil image painter
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(Constants.IMAGE_URL)  // Image URL
            .crossfade(true)
            .build()
    )

    // Image with width, height, and corner radius
    Box(modifier = modifier) {
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator() // Show loading indicator
        }

        Image(
            painter = painter,
            contentDescription = "Image with rounded corners",
            modifier = Modifier
                .width(150.dp)           // Set width
                .height(150.dp)          // Set height
                .clip(RoundedCornerShape(16.dp)), // Set corner radius (rounded corners)
                       // Optional: to take up max width available (if needed)
            contentScale = ContentScale.Crop // Optional: Crop the image to fill the shape
        )
    }
}