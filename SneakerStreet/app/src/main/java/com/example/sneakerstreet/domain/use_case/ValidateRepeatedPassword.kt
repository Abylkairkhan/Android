package com.example.sneakerstreet.domain.use_case

import com.example.sneakerstreet.R
import com.example.sneakerstreet.util.ui.UIText

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(
                success = false,
                errorMessage = UIText.StringResource(R.string.password_dont_match)
            )
        }
        return ValidationResult(
            success = true
        )
    }
}