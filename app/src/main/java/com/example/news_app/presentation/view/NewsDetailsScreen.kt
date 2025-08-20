package com.example.news_app.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.news_app.R
import com.example.news_app.presentation.viewmodel.NewsViewModel
import com.example.news_app.ui.theme.myCustomFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(navController: NavController,itemId: Int,viewModel: NewsViewModel = hiltViewModel()) {
    val item by viewModel.selectedItem.collectAsState()
    viewModel.loadItemById(itemId)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.details_screen),style = TextStyle(
                fontSize = 26.sp,
                color = Color.Black,
                fontFamily = myCustomFontFamily
            )) },  navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            })
        }
    ) { padding ->
        item?.let {
            Column(
                modifier = Modifier .padding(padding)
                    .padding(16.dp)
            ) {
                ImageLoaderView( modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp)))

                Spacer(modifier = Modifier.height(16.dp))
                it.title?.let { text -> Text(text = text ,style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = myCustomFontFamily
                    ))}
                Spacer(modifier = Modifier.height(8.dp))
                it.body?.let { text -> Text(text = text,style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Blue,
                    fontFamily = myCustomFontFamily
                )) }

            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
