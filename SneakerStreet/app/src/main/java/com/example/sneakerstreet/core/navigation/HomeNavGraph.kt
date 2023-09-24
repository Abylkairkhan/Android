package com.example.sneakerstreet.core.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sneakerstreet.presentation.home.Home
import com.example.sneakerstreet.presentation.home.HomeViewModel
import com.example.sneakerstreet.util.route.BottomBarScreen
import com.example.sneakerstreet.util.route.HomeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeNavGraph(

) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        route = BottomBarScreen.Home.route,
        startDestination = HomeScreen.Home.route
    ) {
        composable(
            route = HomeScreen.Home.route
        ) {
            val viewModel = koinViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsState()
            Home(
                state = state,
                obtainEvent = viewModel::obtainEvent,
                navController = navController
            )
        }

        composable(
            route = HomeScreen.ItemDetail.route
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ){
                Text(text = "Item Detail")
            }
        }
    }
}