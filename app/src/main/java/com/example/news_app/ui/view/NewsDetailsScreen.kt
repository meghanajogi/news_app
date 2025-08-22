package com.example.news_app.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.news_app.R
import com.example.news_app.ui.viewmodel.NewsViewModel
import com.example.news_app.ui.theme.myCustomFontFamily
import com.example.domain.utils.Resource


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
        when (val state = item) {

            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Success-> {
                Column(
                    modifier = Modifier .padding(padding)
                        .padding(16.dp)
                )  {
                    ImageLoaderView( modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp)))

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = state.data?.title?:"" ,style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = myCustomFontFamily
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = state.data?.body?:"",style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Blue,
                        fontFamily = myCustomFontFamily
                    ))
                }
            }

            is Resource.Error -> {
                Toast.makeText(LocalContext.current, "${state.message}", Toast.LENGTH_LONG).show()

            }
        }
    }
}
