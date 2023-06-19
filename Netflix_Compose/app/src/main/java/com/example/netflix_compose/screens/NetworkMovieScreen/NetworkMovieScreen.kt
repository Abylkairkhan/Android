package com.example.netflix_compose.screens.NetworkMovieScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.netflix_compose.screens.Additional.MovieNetworkScreenEvent
import com.example.netflix_compose.screens.Additional.MovieNetworkScreenState
import com.example.netflix_compose.ui.theme.Netflix_ComposeTheme

@Composable
fun MovieNetworkScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MovieScreenViewModel
){
    val state by viewModel.movieViewState.collectAsState()

    when(state){
        is MovieNetworkScreenState.ShowProgressBar -> MovieLoading(viewModel)

        is MovieNetworkScreenState.ShowListOfMovie -> MovieList(viewModel)

        is MovieNetworkScreenState.ShowError -> MovieError(viewModel)

        is MovieNetworkScreenState.GoDetails -> {
            Log.d("MyLog", "GoDetails")
            MovieList(viewModel)
        }
    }
    
    LaunchedEffect(key1 = state, block = {
        viewModel.obtainEvent(event = MovieNetworkScreenEvent.EnterScreen)
    })
}

@Composable
private fun MovieLoading(viewModel: MovieScreenViewModel){
    Column {
        Text(text = "ShowProgressBar")
        Button(onClick = {
            viewModel.obtainEvent(event = MovieNetworkScreenEvent.NavigateToDetails(1)) }
        ) {
            Text(text = "Navigate to Details")
        }
    }
}

@Composable
private fun MovieList(viewModel: MovieScreenViewModel){
    Netflix_ComposeTheme {
        val navController = rememberNavController()
        Scaffold(
//            bottomBar = {
//                BottomNavigationBar(
//                    items = listOf(
//                        BottomNavItem(
//                            "Home",
//                            HomeScreen.Movie.route,
//
//                        )
//                    ),
//                    navController = navController,
//                    onClick = {} )
//            }
        ) {
            
        }
    }
}

@Composable
private fun MovieError(viewModel: MovieScreenViewModel){
    Text(text = "ShowError")
}