package com.example.sneakerstreet.util.route

import com.example.sneakerstreet.R

sealed class AuthScreen(val route: String) {

    object Login : AuthScreen("login_screen")

    object Register : AuthScreen("register_screen")

    object ForgotPassword : AuthScreen("forgot_screen")
}

sealed class HomeScreen(val route: String) {

    object Home: HomeScreen("home_screen")

    object ItemDetail: HomeScreen("item_detail_screen")

    object BrandDetail: HomeScreen("brand_detail_screen")
}

sealed class BottomBarScreen(
    val route: String,
    val icon: Int
) {

    object Home : BottomBarScreen(
        "main_screen",
        R.drawable.home_icon
    )

    object Profile : BottomBarScreen(
        "profile_screen",
        R.drawable.person_icon
    )

    object Search : BottomBarScreen(
        "search_screen",
        R.drawable.search_icon
    )

    object Cart : BottomBarScreen(
        "cart_screen",
        R.drawable.shopping_bag_icon
    )
}
