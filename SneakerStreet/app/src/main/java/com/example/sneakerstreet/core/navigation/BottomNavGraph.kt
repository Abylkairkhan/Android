package com.example.sneakerstreet.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sneakerstreet.presentation.cart.Cart
import com.example.sneakerstreet.presentation.home.Home
import com.example.sneakerstreet.presentation.home.HomeViewModel
import com.example.sneakerstreet.presentation.profile.Profile
import com.example.sneakerstreet.presentation.search.Search
import com.example.sneakerstreet.util.route.BottomBarScreen
import com.example.sneakerstreet.util.route.Graph
import org.koin.androidx.compose.koinViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN_ROUTE,
        startDestination = BottomBarScreen.Home.route
    ) {

        composable(
            route = BottomBarScreen.Home.route
        ){
            HomeNavGraph()
        }

        composable(
            route = BottomBarScreen.Profile.route
        ){
            Profile()
        }

        composable(
            route = BottomBarScreen.Search.route
        ){
            Search()
        }

        composable(
            route = BottomBarScreen.Cart.route
        ){
            Cart()
        }
    }
}