package com.talejalilov.yukatechexercise.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.talejalilov.yukatechexercise.util.Screens

@Composable
fun ChooseScreen(navHostController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Button(onClick = {

            })
            {
                Text(text = "Admin Page")
                navHostController.navigate(Screens.AdminLoginScreen.route){

                }
            }
        }

    }
}