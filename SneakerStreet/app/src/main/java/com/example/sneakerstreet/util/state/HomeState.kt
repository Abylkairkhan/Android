package com.example.sneakerstreet.util.state

import com.example.sneakerstreet.domain.models.Product

data class HomeState(
    var showProgress: Boolean = false,
    var productList: ArrayList<Product> = arrayListOf(),
    val error: Boolean = false
)
