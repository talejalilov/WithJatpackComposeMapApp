package com.talejalilov.yukatechexercise.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.talejalilov.yukatechexercise.R
import com.talejalilov.yukatechexercise.presentation.Authentication.AuthenticationViewModel
import com.talejalilov.yukatechexercise.util.Response
import com.talejalilov.yukatechexercise.util.Screens

@Composable
fun SignUpScreen(navHostController: NavHostController,
                viewModel: AuthenticationViewModel = hiltViewModel())
{
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(
            rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val usernameState = remember {
                mutableStateOf("")
            }

            val emailState = remember {
                mutableStateOf("")
            }

            val passwordState = remember {
                mutableStateOf("")
            }

            Image(
                painter = painterResource(id = R.drawable.ic_yuka),
                contentDescription = "Login Logo",
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )

            Text(
                text = "Sign Up",
                modifier = Modifier.padding(10.dp),
                fontSize = 30.sp,
                fontFamily =  FontFamily.SansSerif
            )
            OutlinedTextField(value = usernameState.value, onValueChange = {
                usernameState.value = it
            },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Enter username")
                }
            )

            OutlinedTextField(value = emailState.value, onValueChange = {
                emailState.value = it
            },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Enter your email")
                }
            )

            OutlinedTextField(value = passwordState.value, onValueChange = {
                passwordState.value = it
            },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Enter your password")
                },
                visualTransformation = PasswordVisualTransformation()
            )

            Button(onClick = {
                viewModel.signUp(
                    email = emailState.value,
                    password = passwordState.value,
                    userName = usernameState.value)
            }, modifier = Modifier.padding(8.dp).fillMaxSize()
            ) {
                Text(text = "Sign Up")
                when(val response = viewModel.signUpState.value){

                    is Response.Loading ->{
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize())
                    }

                    is Response.Success ->{
                        if(response.data){
                            navHostController.navigate(Screens.FeedScreen.route){
                                popUpTo(Screens.SignUpScreen.route){
                                    inclusive = true
                                }
                            }
                        }else {
                           // Toast(message = "Sign Up failed")
                        }
                    }

                    is Response.Error ->{

                        Toast(message=response.message)
                    }
                }
            }

            Text(text = "Sign In", color = Color.Blue, modifier = Modifier.padding(8.dp).clickable {
                navHostController.navigate(route = Screens.LoginScreen.route){
                    launchSingleTop  = true
                }
            })
        }
    }
}
