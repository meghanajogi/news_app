package com.example.news_app.presentation.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.news_app.data.model.NewsItem
import com.example.news_app.presentation.viewmodel.NewsViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.news_app.R
import com.example.news_app.ui.theme.myCustomFontFamily
import com.example.news_app.utils.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(navController: NavController, viewModel: NewsViewModel = hiltViewModel()){
    val items by viewModel.items.collectAsState()

    viewModel.loadItems()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.list_screen),style = TextStyle(
                fontSize = 26.sp,
                color = Color.Black,
                fontFamily = myCustomFontFamily
            )) })
        }
    ) { padding ->

        when (val state = items) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Success->{
                LazyColumn(
                    contentPadding = padding,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.data ?: emptyList() ) { item ->
                        ListItem(item = item) {
                            navController.navigate("detail/${item?.id}")
                        }

                    }
                }
            }
            is Resource.Error -> {
                Toast.makeText(LocalContext.current, "${state.message}", Toast.LENGTH_LONG).show()
            }

        }
    }
}

@Composable
fun ListItem(item: NewsItem?, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },

    ) {
        Column(modifier = Modifier.padding(16.dp)) {

Row( modifier = Modifier
    .fillMaxWidth()
    .padding(8.dp), verticalAlignment = Alignment.CenterVertically){
    ImageLoaderView(   modifier = Modifier
        .size(100.dp)
        .clip(RoundedCornerShape(12.dp)))
    Spacer(modifier = Modifier.width(8.dp))
    Text(text = item?.title?:"",  style = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = myCustomFontFamily
    )
    )
}


            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item?.body?:"",  style = TextStyle(
                fontSize = 18.sp,
                color = Color.Blue,
                fontFamily = myCustomFontFamily
            ))
        }
    }
}






