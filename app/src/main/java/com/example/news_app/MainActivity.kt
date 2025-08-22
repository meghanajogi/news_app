package com.example.news_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import com.example.news_app.ui.theme.News_appTheme
import com.example.news_app.ui.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            News_appTheme {
                Surface(color = Color.Transparent) {
                    AppNavGraph()
                }
            }
        }
    }
}