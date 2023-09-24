package com.example.sneakerstreet.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.sneakerstreet.data.auth.AuthRepository
import com.example.sneakerstreet.domain.use_case.ValidateEmail
import com.example.sneakerstreet.domain.use_case.ValidatePassword
import com.example.sneakerstreet.util.event.EventHandler
import com.example.sneakerstreet.util.event.SignInFormEvent
import com.example.sneakerstreet.util.route.AuthScreen
import com.example.sneakerstreet.util.route.Graph
import com.example.sneakerstreet.util.state.RegistrationFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(
    private val authRepository: AuthRepository,
    private var validateEmail: ValidateEmail,
    private var validatePassword: ValidatePassword
) : ViewModel(), EventHandler<SignInFormEvent> {

    private var _state: MutableStateFlow<RegistrationFormState> =
        MutableStateFlow(RegistrationFormState())
    var state = _state.asStateFlow()

    override fun obtainEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> _state.value =
                _state.value.copy(email = event.email)

            is SignInFormEvent.PasswordChanged -> _state.value =
                _state.value.copy(password = event.password)

            is SignInFormEvent.NavigateToRegister-> event.navController.navigate(AuthScreen.Register.route)

            is SignInFormEvent.Submit -> submitData(event.navController)

            is SignInFormEvent.CheckUserAlreadyInSystem -> userAlreadyInSystem(event.navController)
        }
    }

    private fun userAlreadyInSystem(navController: NavHostController){
        val result = authRepository.checkUserAlreadyInSystem()
        if (result.success){
            navController.navigate(Graph.MAIN_ROUTE)
        }
    }

    private fun submitData(navController: NavHostController) {
        val emailResult = validateEmail.execute(_state.value.email)
        val passwordResult = validatePassword.execute(_state.value.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.success }

        if (hasError) {
            _state.value = _state.value.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }

        _state.value = _state.value.copy(
            showProgressBar = true
        )

        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.signInUser(_state.value.email, _state.value.password)
            withContext(Dispatchers.Main) {
                if (result.success) {
                    navController.navigate(Graph.MAIN_ROUTE) {
                        popUpTo(navController.graph.id)
                    }
                } else {
                    _state.value = _state.value.copy(
                        passwordError = result.error,
                        showProgressBar = false
                    )
                }
            }
        }
    }
}