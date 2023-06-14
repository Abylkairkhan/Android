package com.example.netflix_compose.navigation

sealed class Screen(val route: String){

    object LoginScreen: Screen("login_screen")

    object RegisterScreen: Screen("register_screen")

    object MovieNetworkScreen: Screen("movie_network_screen")

    object MovieSavedScreen: Screen("movie_saved_screen")
}
