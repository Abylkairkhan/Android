package com.example.sneakerstreet.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sneakerstreet.data.auth.AuthRepository
import com.example.sneakerstreet.util.BottomNavigation
import com.example.sneakerstreet.util.route.Graph

@Composable
fun AppNavigation(
    navController: NavHostController,
    authRepository: AuthRepository
) {
    val startDestination =

        if (authRepository.checkUserAlreadyInSystem().success)
            Graph.MAIN_ROUTE
        else
            Graph.AUTH_ROUTE

    NavHost(
        navController = navController,
        route = Graph.ROOT_ROUTE,
        startDestination = startDestination
    ) {
        authGraph(
            navController
        )
        composable(
            route = Graph.MAIN_ROUTE
        ) {
            BottomNavigation()
        }
    }
}