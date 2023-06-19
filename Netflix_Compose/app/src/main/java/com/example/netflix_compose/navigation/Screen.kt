package com.example.netflix_compose.navigation

sealed class AuthScreen(val route: String){
    object Login: AuthScreen("login_screen")

    object Register: AuthScreen("register_screen")

    object Forgot: AuthScreen("forgot_screen")
}

sealed class HomeScreen(val route: String){
    object Movie: AuthScreen("movie_screen")
    object Saved: AuthScreen("saved_screen")
    object Profile: AuthScreen("profile_screen")
    object Detail: AuthScreen("details_screen")
}

sealed class DetailScreen(val route: String){
    object Information : DetailScreen(route = "Information_screen")
    object Overview : DetailScreen(route = "Overview_screen")
}
