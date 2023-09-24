package com.example.sneakerstreet.domain.use_case

import com.example.sneakerstreet.R
import com.example.sneakerstreet.data.auth.AuthResult
import com.example.sneakerstreet.util.ui.UIText
import com.google.firebase.auth.FirebaseAuthException

class AuthErrorCatcher {

    fun errorCatcher(e: FirebaseAuthException, authResult: AuthResult) {
        when (e.errorCode) {
            "ERROR_INVALID_EMAIL" -> {
                authResult.error = UIText.StringResource(R.string.error_invalid_email)
            }

            "ERROR_WRONG_PASSWORD" -> {
                authResult.error = UIText.StringResource(R.string.error_wrong_password)
            }

            "ERROR_USER_NOT_FOUND" -> {
                authResult.error = UIText.StringResource(R.string.error_user_not_found)
            }

            "ERROR_USER_DISABLED" -> {
                authResult.error = UIText.StringResource(R.string.error_user_disabled)
            }

            "ERROR_TOO_MANY_REQUESTS" -> {
                authResult.error = UIText.StringResource(R.string.error_too_many_request)
            }

            "ERROR_OPERATION_NOT_ALLOWED" -> {
                authResult.error = UIText.StringResource(R.string.error_not_allowed)
            }

            "ERROR_EMAIL_ALREADY_IN_USE" -> {
                authResult.error = UIText.StringResource(R.string.error_already_in_use)
            }

            else -> {
                authResult.error = UIText.StringResource(R.string.error_undefined)
            }
        }
    }
}