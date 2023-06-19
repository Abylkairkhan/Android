package com.example.netflix_compose.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.netflix_compose.navigation.AuthScreen
import com.example.netflix_compose.screens.LoginScreen
import com.example.netflix_compose.screens.LoginScreen.ForgotScreen
import com.example.netflix_compose.screens.LoginScreen.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(navController = navController)
        }
        composable(route = AuthScreen.Register.route){
            RegisterScreen(navController = navController)
        }
        composable(route = AuthScreen.Forgot.route){
            ForgotScreen(navController = navController)
        }
    }
}