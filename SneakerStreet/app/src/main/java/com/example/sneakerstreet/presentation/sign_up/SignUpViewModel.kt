package com.example.sneakerstreet.presentation.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.sneakerstreet.data.auth.AuthRepository
import com.example.sneakerstreet.domain.use_case.ValidateEmail
import com.example.sneakerstreet.domain.use_case.ValidatePassword
import com.example.sneakerstreet.domain.use_case.ValidateRepeatedPassword
import com.example.sneakerstreet.util.event.EventHandler
import com.example.sneakerstreet.util.event.RegistrationFormEvent
import com.example.sneakerstreet.util.event.SignInFormEvent
import com.example.sneakerstreet.util.route.AuthScreen
import com.example.sneakerstreet.util.state.RegistrationFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(
    private val authRepository: AuthRepository,
    private var validateEmail: ValidateEmail,
    private var validatePassword: ValidatePassword,
    private var validateRepeatedPassword: ValidateRepeatedPassword
) : ViewModel(), EventHandler<RegistrationFormEvent> {

    private var _state: MutableStateFlow<RegistrationFormState> =
        MutableStateFlow(RegistrationFormState())
    var state = _state.asStateFlow()

    override fun obtainEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> _state.value =
                _state.value.copy(email = event.email)


            is RegistrationFormEvent.PasswordChanged -> _state.value =
                _state.value.copy(password = event.password)

            is RegistrationFormEvent.RepeatedPasswordChange -> _state.value =
                _state.value.copy(repeatedPassword = event.repeatedPassword)

            is RegistrationFormEvent.NavigateToSignIn -> event.navController.navigate(AuthScreen.Login.route){
                popUpTo(AuthScreen.Register.route){
                    inclusive = true
                }
            }

            is RegistrationFormEvent.Submit -> submitData(event.navController)

        }
    }

    private fun submitData(navController: NavHostController) {
        val emailResult = validateEmail.execute(_state.value.email)
        val passwordResult = validatePassword.execute(_state.value.password)
        val repeatedPasswordResult =
            validateRepeatedPassword.execute(_state.value.password, _state.value.repeatedPassword)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult
        ).any { !it.success }

        if (hasError) {
            _state.value = _state.value.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage
            )
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.createUser(_state.value.email, _state.value.password)
            withContext(Dispatchers.Main) {
                if (result.success) {
                    _state.value = _state.value.copy(
                        showSnackBar = true
                    )
                    delay(3000)
                    navController.popBackStack()
                } else {
                    _state.value = _state.value.copy(
                        repeatedPasswordError = result.error
                    )
                }
            }
        }
    }
}