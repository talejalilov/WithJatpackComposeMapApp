package com.talejalilov.yukatechexercise.presentation.Authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.talejalilov.yukatechexercise.R
import com.talejalilov.yukatechexercise.util.Screens

@Composable
fun ChooseScreen(navHostController: NavHostController) {
    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(

            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
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
                    .padding(16.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Red
                    ),
                    onClick = {
                        navHostController.navigate(route = Screens.AdminLoginScreen.route) {
                            popUpTo(Screens.ChooseScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Admin Page",
                        color = Color.Black,
                        fontSize = 35.sp
                    )
                }
            }
            Card(
                Modifier
                    .weight(2f)
                    .padding(16.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Red
                    ),
                    onClick = {
                        navHostController.navigate(route = Screens.UserLoginScreen.route) {
                            popUpTo(Screens.ChooseScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(
                        text = "User Page",
                        color = Color.Black,
                        fontSize = 35.sp
                    )
                }
            }
        }
    }
}
