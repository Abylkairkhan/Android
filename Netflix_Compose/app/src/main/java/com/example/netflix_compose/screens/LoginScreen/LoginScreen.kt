package com.example.netflix_compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.netflix_compose.R
import com.example.netflix_compose.navigation.AuthScreen
import com.example.netflix_compose.navigation.HomeScreen
import com.example.netflix_compose.navigation.graphs.Graph
import com.example.netflix_compose.ui.theme.Netflix_ComposeTheme

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier
){
    Netflix_ComposeTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var passwordVisibility by remember { mutableStateOf(false) }
            var icon = if(passwordVisibility) 
                painterResource(id = R.drawable.visibility)
            else
                painterResource(id = R.drawable.visibility_off)
            val maxLength = 15

            OutlinedTextField(
                value = username,
                onValueChange = {
                    if(maxLength >= it.length) username = it
                },
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 21.sp
                ),
                label = {
                    Text(
                        text = "Username",
                        color = MaterialTheme.colorScheme.onPrimary)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Username Icon",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = modifier
                            .size(40.dp)
                            .padding(
                                start = 5.dp,
                                end = 5.dp
                            ))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Go
                ),
                modifier = modifier
                    .padding(10.dp),
                maxLines = 1
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    if(maxLength >= it.length) password = it
                },
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 21.sp
                ),
                label = {
                    Text(
                        text = "Password",
                        color = MaterialTheme.colorScheme.onPrimary)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisibility = !passwordVisibility
                        }
                    ) {
                        Icon(
                            painter = icon,
                            contentDescription = "Password Visibility Icon",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = modifier
                                .size(100.dp)
                                .padding(
                                    start = 5.dp,
                                    end = 10.dp
                                )
                        )
                    }
                },
                visualTransformation = if(passwordVisibility)
                                            VisualTransformation.None
                                        else
                                            PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Password Icon",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = modifier
                            .size(40.dp)
                            .padding(
                                start = 5.dp,
                                end = 5.dp
                            )
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Go
                ),
                maxLines = 1
            )
            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                elevation = ButtonDefaults.buttonElevation(0.dp),
                contentPadding = PaddingValues(20.dp, 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.surface
                ),
                modifier = modifier
                    .padding(20.dp)
                    .width(200.dp)
            ){
                Text(
                    text = "Next",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 21.sp,
                )
            }
            Button(
                onClick = {
                    navController.navigate(AuthScreen.Register.route)
                },
                elevation = ButtonDefaults.buttonElevation(0.dp),
                contentPadding = PaddingValues(20.dp, 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.surface
                ),
                modifier = modifier
                    .padding(20.dp)
                    .width(200.dp)
            ){
                Text(
                    text = "Register",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 21.sp,
                )
            }
            Button(
                onClick = {
                    navController.navigate(AuthScreen.Forgot.route)
                },
                elevation = ButtonDefaults.buttonElevation(0.dp),
                contentPadding = PaddingValues(20.dp, 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.surface
                ),
                modifier = modifier
                    .padding(20.dp)
                    .width(200.dp)
            ){
                Text(
                    text = "Forgot",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 21.sp,
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    val navController = rememberNavController()
    LoginScreen(navController = navController, Modifier)
}