package com.example.sneakerstreet.util.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakerstreet.R


@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CircularProgressIndicator(
            strokeWidth = 7.dp,
            modifier = Modifier
                .size(100.dp)
        )
    }
}

@Preview
@Composable
fun ProgressBar() {
    Loading()
}


@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "LogoImage",
        modifier = Modifier
    )
}

@Composable
fun TextInput(
    value: String,
    error: Boolean,
    errorMessage: String?,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        label = {
            Text(text = stringResource(id = R.string.username))
        },
        placeholder = {
            Text(text = stringResource(id = R.string.enter_your_username))
        },
        onValueChange = onValueChange,
        isError = error,
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        visualTransformation = VisualTransformation.None,
        supportingText = {
            AnimatedVisibility(
                visible = error,
                enter = fadeIn(
                    animationSpec = tween(durationMillis = 1000)
                )
            ) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .width(260.dp)
                )
            }
        },
        modifier = Modifier
            .padding(vertical = 6.dp)
    )
}

@Composable
fun PasswordInput(
    label: String,
    value: String,
    error: Boolean,
    errorMessage: String?,
    onValueChange: (String) -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = stringResource(id = R.string.enter_your_password))
        },
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        isError = error,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null)
        },
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "hide_password"
                    )
                }
            } else {
                IconButton(
                    onClick = { showPassword = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "hide_password"
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        supportingText = {
            AnimatedVisibility(
                visible = error,
                enter = fadeIn(
                    animationSpec = tween(durationMillis = 1000)
                )
            ) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .width(260.dp)
                )
            }
        },
        modifier = Modifier
            .padding(vertical = 6.dp)
    )
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
        modifier = Modifier
            .size(width = 210.dp, height = 70.dp)
            .padding(vertical = 6.dp)
            .bounceClick()
    ) {
        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}

@Composable
fun DoNotHaveAccount(
    text: String,
    buttonText: String,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text)
        TextButton(
            onClick = onClick
        ) {
            Text(text = buttonText)
        }
    }
}