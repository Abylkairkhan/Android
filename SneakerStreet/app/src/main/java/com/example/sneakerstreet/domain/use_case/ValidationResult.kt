package com.example.sneakerstreet.domain.use_case

import com.example.sneakerstreet.util.ui.UIText

data class ValidationResult(
    val success: Boolean,
    val errorMessage: UIText? = null
)
