package com.example.netflix_compose.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.netflix_compose.screens.HomeScreen.BottomNavigation

@Composable
fun Navigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION){

        authNavGraph(navController = navController)

        composable(route = Graph.HOME){
            BottomNavigation()
        }
    }
}
object Graph{
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "authentication _graph"
    const val HOME = "home_graph"
}