package com.example.sneakerstreet.domain.use_case

import com.example.sneakerstreet.R
import com.example.sneakerstreet.util.PASSWORD_MIN_LENGTH
import com.example.sneakerstreet.util.ui.UIText

class ValidatePassword {

    fun execute(password: String): ValidationResult {

        if (password.length < PASSWORD_MIN_LENGTH) {
            return ValidationResult(
                success = false,
                errorMessage = UIText.StringResource(R.string.small_password)
            )
        }

        val containsLetterAndDigit = password.any { it.isDigit() } && password.any { it.isLetter() }

        if (!containsLetterAndDigit) {
            return ValidationResult(
                success = false,
                errorMessage = UIText.StringResource(R.string.password_error_digit_letter)
            )
        }

        return ValidationResult(success = true)
    }
}