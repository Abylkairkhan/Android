package com.example.netflix_compose.screens.NetworkMovieScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.netflix_compose.screens.models.MovieNetworkScreenEvent
import com.example.netflix_compose.screens.models.MovieNetworkScreenState

@Composable
fun MovieNetworkScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: MovieScreenViewModel
){

    val state by viewModel.movieViewState.collectAsState()
    Log.d("MyLog", "$state")

    when(state){
        is MovieNetworkScreenState.ShowProgressBar -> {
            Log.d("MyLog", "ShowProgressBar")
            MovieLoading(viewModel)
        }

        is MovieNetworkScreenState.ShowListOfMovie -> MovieLoading(viewModel)

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
            Log.d("MyLog", "Click")
            viewModel.obtainEvent(event = MovieNetworkScreenEvent.NavigateToDetails(1)) }
        ) {
            Text(text = "Navigate to Details")
        }
    }
}

@Composable
private fun MovieList(viewModel: MovieScreenViewModel){
    Column {
        Text(text = "ShowListOfMovie")
        Button(onClick = {
            Log.d("MyLog", "Click")
            viewModel.obtainEvent(event = MovieNetworkScreenEvent.NavigateToDetails(-1)) }
        ) {
            Text(text = "Navigate to Eror")
        }
    }
}

@Composable
private fun MovieError(viewModel: MovieScreenViewModel){
    Text(text = "ShowError")
}