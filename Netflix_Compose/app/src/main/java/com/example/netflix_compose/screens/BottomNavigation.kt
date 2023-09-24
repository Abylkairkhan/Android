package com.example.netflix_compose.screens.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.netflix_compose.models.BottomNavItem
import com.example.netflix_compose.navigation.HomeScreen
import com.example.netflix_compose.navigation.graphs.HomeNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigation(
    navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                onClick = {
                    val currentDestination = navController.currentDestination?.route
                    val selectedDestination = when (it.name) {
                        "Home" -> HomeScreen.Movie.route
                        "Saved" -> HomeScreen.Saved.route
                        "Profile" -> HomeScreen.Profile.route
                        else -> null
                    }

                    if (selectedDestination != null && currentDestination != selectedDestination)
                        navController.navigate(selectedDestination)

                })
        }
    ) {
        HomeNavGraph(navHostController = navController)
    }
}


@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    onClick: (BottomNavItem) -> Unit
){
    val items = listOf(
        BottomNavItem(
            "Home",
            "movie_screen",
            Icons.Filled.Home
        ),
        BottomNavItem(
            "Saved",
            "saved_screen",
            Icons.Filled.List
        ),
        BottomNavItem(
            "Profile",
            "profile_screen",
            Icons.Filled.AccountCircle
        )
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier
            .height(70.dp),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        tonalElevation = 5.dp
    ){
        items.forEach{ item ->

            val selected = item.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                onClick = { onClick(item) },
                selected = selected,
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if(selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            )
        }
    }
}