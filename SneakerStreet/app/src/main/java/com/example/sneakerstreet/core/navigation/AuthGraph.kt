package com.example.sneakerstreet.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sneakerstreet.presentation.sign_in.SignIn
import com.example.sneakerstreet.presentation.sign_in.SignInViewModel
import com.example.sneakerstreet.presentation.sign_up.SignUp
import com.example.sneakerstreet.presentation.sign_up.SignUpViewModel
import com.example.sneakerstreet.util.route.AuthScreen
import com.example.sneakerstreet.util.route.Graph
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.authGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.AUTH_ROUTE,
        startDestination = AuthScreen.Login.route
    ) {
        composable(
            route = AuthScreen.Login.route
        ) {
            val viewModel = koinViewModel<SignInViewModel>()
            val state by viewModel.state.collectAsState()
            SignIn(
                state = state,
                obtainEvent = viewModel::obtainEvent,
                navController = navController,
                modifier = Modifier
            )
        }
        composable(
            route = AuthScreen.Register.route
        ) {
            val viewModel = koinViewModel<SignUpViewModel>()
            val state by viewModel.state.collectAsState()
            SignUp(
                state = state,
                obtainEvent = viewModel::obtainEvent,
                navController = navController,
                modifier = Modifier
            )
        }
        composable(
            route = AuthScreen.ForgotPassword.route
        ) {
            Text(text = "Forgot")
        }
    }
}