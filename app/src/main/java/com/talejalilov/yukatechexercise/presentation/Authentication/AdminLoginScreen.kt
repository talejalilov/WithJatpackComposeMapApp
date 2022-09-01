package com.talejalilov.yukatechexercise.presentation.Authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.talejalilov.yukatechexercise.R
import com.talejalilov.yukatechexercise.presentation.Toast
import com.talejalilov.yukatechexercise.presentation.viewmodel.AuthenticationViewModel
import com.talejalilov.yukatechexercise.util.Response
import com.talejalilov.yukatechexercise.util.Screens

@Composable
fun AdminLoginScreen(
    navHostController: NavHostController,
    viewModel: AuthenticationViewModel = hiltViewModel()
) {
    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(

            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val emailState = remember {
                mutableStateOf("")
            }

            val passwordState = remember {
                mutableStateOf("")
            }

            val isPasswordVisible = remember {
                mutableStateOf(false)
            }

            val isFormValid = derivedStateOf {
                emailState.value.isNotBlank() && passwordState.value.length >= 7
            }

            Image(
                painter = painterResource(id = R.drawable.ic_yuka),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(1f)
                    .size(200.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )

            Card(
                Modifier
                    .weight(2f)
                    .padding(8.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(text = "Welcome YUKA APP!", fontWeight = FontWeight.Bold, fontSize = 32.sp)

                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = emailState.value,
                            onValueChange = { emailState.value = it },
                            label = { Text(text = "Email") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Done
                            ),
                            trailingIcon = {
                                if (emailState.value.isNotBlank()) {
                                    IconButton(onClick = { emailState.value = "" }) {
                                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = passwordState.value,
                            onValueChange = { passwordState.value = it },
                            label = { Text(text = "Password") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = {
                                    isPasswordVisible.value = !isPasswordVisible.value
                                }) {
                                    Icon(
                                        imageVector = if (isPasswordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Password Toggle"
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            enabled = isFormValid.value,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = Color.Red
                            ),
                            onClick = {
                                viewModel.signIn(
                                    email = emailState.value,
                                    password = passwordState.value
                                )
                            }
                        ) {
                            Text(
                                text = "Sign In",
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = {
                                navHostController.navigate(route = Screens.SignUpScreen.route) {
                                    launchSingleTop = true
                                }
                            }) {
                                Text(text = "Admin For Sign Up")
                            }
                        }
                        when (val response = viewModel.signInState.value) {
                            is Response.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                )
                            }

                            is Response.Success -> {
                                if (response.data) {
                                    LaunchedEffect(key1 = true) {
                                        navHostController.navigate(Screens.FeedScreen.route) {
                                            popUpTo(Screens.SignUpScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                }
                            }

                            is Response.Error -> {
                                Toast(message = response.message)
                            }
                        }
                    }
                }
            }
        }
    }
}
