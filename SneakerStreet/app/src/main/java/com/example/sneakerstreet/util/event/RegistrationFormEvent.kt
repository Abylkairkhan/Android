package com.example.sneakerstreet.util.event

import androidx.navigation.NavHostController

sealed class RegistrationFormEvent {

    data class EmailChanged(val email: String) : RegistrationFormEvent()

    data class PasswordChanged(val password: String) : RegistrationFormEvent()

    data class RepeatedPasswordChange(val repeatedPassword: String) : RegistrationFormEvent()

    data class NavigateToSignIn(val navController: NavHostController) : RegistrationFormEvent()

    data class Submit(val navController: NavHostController) : RegistrationFormEvent()
}