package com.example.sneakerstreet.util

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sneakerstreet.core.navigation.BottomNavGraph
import com.example.sneakerstreet.util.route.BottomBarScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigation() {
    val navHostController = rememberNavController()
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
            bottomBar = {
                BottomBar(navHostController = navHostController)
            }
        ) {
            BottomNavGraph(navController = navHostController)
        }
    }
}

@Composable
fun BottomBar(
    navHostController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Search,
        BottomBarScreen.Cart,
        BottomBarScreen.Profile,
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        modifier = Modifier
            .height(60.dp)
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {

    val isSelected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    val animatedHeight by animateDpAsState(targetValue = if (isSelected) 36.dp else 26.dp)
    val animatedWeight by animateFloatAsState(targetValue = if (isSelected) 1.5f else 1f)
    val animationRotation by animateFloatAsState(
        targetValue = if (isSelected) 360f else 0f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )

    NavigationBarItem(
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigate Icon"
            )
        },
        selected = isSelected,
        onClick = {
            navHostController.navigate(screen.route) {
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        modifier = Modifier
            .height(animatedHeight)
            .weight(animatedWeight)
            .graphicsLayer { rotationX = animationRotation }
    )
}
