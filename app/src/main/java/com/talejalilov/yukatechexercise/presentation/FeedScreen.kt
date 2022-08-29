package com.talejalilov.yukatechexercise.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun FeedScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "FeedScreen")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.FEED, navController =navController)
    }
}