package com.example.sneakerstreet.presentation.home

import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.sneakerstreet.data.firestore.DataBaseRepository
import com.example.sneakerstreet.util.event.EventHandler
import com.example.sneakerstreet.util.event.HomeEvent
import com.example.sneakerstreet.util.route.HomeScreen
import com.example.sneakerstreet.util.state.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val dataBaseRepository: DataBaseRepository
): ViewModel(), EventHandler<HomeEvent> {

    private var _state: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState())
    var state = _state.asStateFlow()

    override fun obtainEvent(event: HomeEvent) {
        when (event){
            is HomeEvent.LoadData -> getProductList()
            is HomeEvent.LikedProduct -> likedProduct(event.id)
            is HomeEvent.SelectProductByID -> selectProduct(event.id, event.navController)
        }
    }

    private fun selectProduct(id: String, navController: NavHostController){
        navController.navigate(HomeScreen.ItemDetail.route)
    }

    private fun likedProduct(id: String) {

    }

    private fun getProductList() {
        _state.value = _state.value.copy(
            showProgress = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            val result = dataBaseRepository.getProduct()
            withContext(Dispatchers.IO){
                _state.value = _state.value.copy(
                    showProgress = false,
                    productList = result
                )
            }
        }
    }
}