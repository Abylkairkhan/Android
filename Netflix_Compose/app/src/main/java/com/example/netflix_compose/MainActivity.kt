package com.example.netflix_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.netflix_compose.ui.theme.Netflix_ComposeTheme
import com.example.netflix_compose.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Netflix_ComposeTheme {
                Navigation()
            }
        }
    }
}
