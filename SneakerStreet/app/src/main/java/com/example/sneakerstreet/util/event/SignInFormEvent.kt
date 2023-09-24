package com.example.sneakerstreet.util.event

import androidx.navigation.NavHostController

sealed class SignInFormEvent {

    data class EmailChanged(val email: String) : SignInFormEvent()

    data class PasswordChanged(val password: String) : SignInFormEvent()

    data class NavigateToRegister(val navController: NavHostController) : SignInFormEvent()

    data class Submit(val navController: NavHostController) : SignInFormEvent()

    data class CheckUserAlreadyInSystem(val navController: NavHostController) : SignInFormEvent()
}