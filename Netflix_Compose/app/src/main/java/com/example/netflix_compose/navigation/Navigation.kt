package com.example.netflix_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.netflix_compose.screens.LoginScreen
import com.example.netflix_compose.screens.NetworkMovieScreen.MovieNetworkScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){

        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController = navController)
        }

        composable(route = Screen.RegisterScreen.route){

        }

        composable(route = Screen.MovieNetworkScreen.route){
            MovieNetworkScreen(navController)
        }

        composable(route = Screen.MovieSavedScreen.route){

        }
    }
}