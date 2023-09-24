package com.example.sneakerstreet.util.state

import com.example.sneakerstreet.util.ui.UIText

data class RegistrationFormState(
    val email: String = "",
    val emailError: UIText? = null,
    val password: String = "",
    val passwordError: UIText? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: UIText? = null,
    val showProgressBar: Boolean = false,
    val showSnackBar: Boolean = false
)
