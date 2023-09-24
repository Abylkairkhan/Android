package com.example.sneakerstreet.domain.use_case

import android.util.Patterns
import com.example.sneakerstreet.R
import com.example.sneakerstreet.util.ui.UIText

class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                success = false,
                errorMessage = UIText.StringResource(R.string.blank_email)
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                success = false,
                errorMessage = UIText.StringResource(R.string.thats_not_valid_email)
            )
        }
        return ValidationResult(
            success = true
        )
    }
}