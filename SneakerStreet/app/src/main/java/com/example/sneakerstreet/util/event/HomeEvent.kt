package com.example.sneakerstreet.util.event

import androidx.navigation.NavHostController

sealed class HomeEvent {

    object LoadData: HomeEvent()

    data class LikedProduct(var id: String): HomeEvent()

    data class SelectProductByID(var id: String, var navController: NavHostController): HomeEvent()
}