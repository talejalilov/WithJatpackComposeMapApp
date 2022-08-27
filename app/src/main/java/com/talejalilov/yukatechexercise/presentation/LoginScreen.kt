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
import androidx.compose.ui.text.font.FontWeight
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
fun LoginScreen (
    navHostController: NavHostController,
    viewModel: AuthenticationViewModel = hiltViewModel())
{
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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
                    .padding(top = 36.dp)
                    .padding(8.dp)
                )

            Text(
                text = "Sign In",
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                fontFamily =  FontFamily.SansSerif
            )

            OutlinedTextField(value = emailState.value, onValueChange = {
                emailState.value = it
            },
                modifier = Modifier.padding(top =50.dp),
                label = {
                    Text(text = "Enter Your email")
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
                viewModel.signIn(
                    email = emailState.value,
                    password = passwordState.value)
            }, modifier = Modifier.padding(8.dp),
            ) {  
                Text(text = "Sign In")
                when(val response = viewModel.signInState.value){

                    is Response.Loading ->{
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize())
                    }

                    is Response.Success ->{
                        if(response.data){
                            navHostController.navigate(Screens.FeedScreen.route){
                                popUpTo(Screens.LoginScreen.route){
                                    inclusive = true
                                }
                            }
                    }else {
                        Toast(message = "No User")
                    }
                    }

                    is Response.Error ->{

                        Toast(message=response.message)
                    }
                }
            }

            Text(text = "Sign Up", color = Color.Blue, modifier = Modifier.padding(8.dp).clickable {
                navHostController.navigate(route = Screens.SignUpScreen.route){
                    launchSingleTop  = true
                }
            }
            )
        }
    }

}