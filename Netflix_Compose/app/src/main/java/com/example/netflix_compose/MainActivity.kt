package com.example.netflix_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.compose.rememberNavController
import com.example.netflix_compose.navigation.graphs.Navigation
import com.example.netflix_compose.ui.theme.Netflix_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Netflix_ComposeTheme {
                Navigation(navController = rememberNavController())
            }
        }
    }
}
