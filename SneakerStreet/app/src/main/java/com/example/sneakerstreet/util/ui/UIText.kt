package com.example.sneakerstreet.util.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UIText {

    data class DynamicString(
        val value: String
    ) : UIText()

    class StringResource(
        @StringRes val resID: Int,
        vararg val args: Any
    ) : UIText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = resID, *args)
        }
    }
}
