package com.example.sneakerstreet.presentation.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sneakerstreet.R
import com.example.sneakerstreet.ui.theme.SneakerStreetTheme
import com.example.sneakerstreet.util.event.RegistrationFormEvent
import com.example.sneakerstreet.util.state.RegistrationFormState
import com.example.sneakerstreet.util.ui.CustomButton
import com.example.sneakerstreet.util.ui.DoNotHaveAccount
import com.example.sneakerstreet.util.ui.Logo
import com.example.sneakerstreet.util.ui.PasswordInput
import com.example.sneakerstreet.util.ui.TextInput

@Composable
fun SignUp(
    state: RegistrationFormState,
    obtainEvent: (RegistrationFormEvent) -> Unit,
    navController: NavHostController,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        TextInput(
            value = state.email,
            error = state.emailError != null,
            errorMessage = if (state.emailError != null)
                state.emailError!!.asString()
            else null,
            onValueChange = {
                obtainEvent(RegistrationFormEvent.EmailChanged(it))
            }
        )
        PasswordInput(
            stringResource(id = R.string.password),
            value = state.password,
            error = state.passwordError != null,
            errorMessage = if (state.passwordError != null)
                state.passwordError!!.asString()
            else null,
            onValueChange = {
                obtainEvent(RegistrationFormEvent.PasswordChanged(it))
            }
        )
        PasswordInput(
            stringResource(id = R.string.password),
            value = state.repeatedPassword,
            error = state.repeatedPasswordError != null,
            errorMessage = if (state.repeatedPasswordError != null)
                state.repeatedPasswordError!!.asString()
            else null,
            onValueChange = {
                obtainEvent(RegistrationFormEvent.RepeatedPasswordChange(it))
            }
        )
        Spacer(modifier = Modifier.size(100.dp))
        CustomButton(
            text = stringResource(id = R.string.sign_up),
            onClick = {
                obtainEvent(RegistrationFormEvent.Submit(navController))
            }
        )
        if (state.showSnackBar){
            Snackbar(
                modifier = Modifier
                    .padding(3.dp)
            ){
                Text(text = stringResource(id = R.string.account_created))
            }
        }
        else {
            Divider(
                color = MaterialTheme.colorScheme.outline,
                thickness = 2.dp,
                modifier = modifier
                    .padding(top = 24.dp)
                    .width(300.dp)
            )
            DoNotHaveAccount(
                text = stringResource(id = R.string.already_have_account),
                buttonText = stringResource(id = R.string.sign_in),
                onClick = {
                    obtainEvent(RegistrationFormEvent.NavigateToSignIn(navController))
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSignUp() {
    SneakerStreetTheme {
        SignUp(
            state = RegistrationFormState(),
            obtainEvent = {},
            navController = rememberNavController(),
            modifier = Modifier
        )
    }
}

