package com.example.netflix_compose.navigation.graphs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.netflix_compose.navigation.HomeScreen
import com.example.netflix_compose.screens.DetailsScreen.DetailScreen
import com.example.netflix_compose.screens.HomeScreen.HomeScreen
import com.example.netflix_compose.screens.SavedScreen.SavedScreen

@Composable
fun HomeNavGraph(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = HomeScreen.Movie.route
    ) {
        composable(route = HomeScreen.Movie.route) {
            HomeScreen(
                navController = navHostController
            )
        }

        composable(
            route = HomeScreen.Detail.route + "/{id}" + "/{type}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                },
                navArgument("type") {
                    type = NavType.BoolType
                }
            )
        ) {
            var id = it.arguments?.getInt("id")
            var type = it.arguments?.getBoolean("type")
            if (id != null && type != null) {
                DetailScreen(movie_id = id, navController = navHostController, type = type!!)
            }
        }

        composable(route = HomeScreen.Saved.route) {
            SavedScreen(navController = navHostController)
        }

        composable(route = HomeScreen.Profile.route) {
            Text(text = "HomeScreen.Profile")
        }
    }
}

