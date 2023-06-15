package com.example.netflix_compose.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.netflix_compose.screens.LoginScreen
import com.example.netflix_compose.screens.NetworkMovieScreen.MovieNetworkScreen
import com.example.netflix_compose.screens.NetworkMovieScreen.MovieScreenViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){

        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController = navController, Modifier)
        }

        composable(route = Screen.RegisterScreen.route){

        }

        composable(route = Screen.MovieNetworkScreen.route){
            MovieNetworkScreen(
                navController = navController,
                modifier = Modifier,
                viewModel = MovieScreenViewModel()
            )
        }

        composable(route = Screen.MovieSavedScreen.route){

        }
    }
}