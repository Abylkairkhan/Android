package com.example.netflix_compose.navigation.graphs

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.netflix.request.Service
import com.example.netflix_compose.navigation.DetailScreen
import com.example.netflix_compose.navigation.HomeScreen
import com.example.netflix_compose.screens.DetailsScreen.DetailScreen
import com.example.netflix_compose.screens.HomeScreen.HomeScreen
import com.example.netflix_compose.screens.HomeScreen.HomeViewModel

@Composable
fun HomeNavGraph(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = HomeScreen.Movie.route){
        composable(route = HomeScreen.Movie.route){
            HomeScreen(
                navController = navHostController,
                viewModel = HomeViewModel()
            )
        }

        composable(
            route = HomeScreen.Detail.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ){
            it.arguments?.getInt("id")?.let { it1 -> DetailScreen(movie_id = it1) }
        }

        composable(route = HomeScreen.Saved.route){
            Text(text = "HomeScreen.Saved")
        }

        composable(route = HomeScreen.Profile.route){
            Text(text = "HomeScreen.Profile")
        }

    }
}

