package com.talejalilov.yukatechexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.talejalilov.yukatechexercise.presentation.FeedScreen
import com.talejalilov.yukatechexercise.presentation.LoginScreen
import com.talejalilov.yukatechexercise.presentation.SignUpScreen
import com.talejalilov.yukatechexercise.presentation.SplashScreen
import com.talejalilov.yukatechexercise.ui.theme.YukaTechExerciseTheme
import com.talejalilov.yukatechexercise.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YukaTechExerciseTheme {
                    val navController = rememberNavController()
                    MapApplication(navController)
            }
        }
    }
}

@Composable
fun MapApplication(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navHostController = navController)
        }

        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navHostController = navController)
        }

        composable(route = Screens.FeedScreen.route) {
            FeedScreen(navHostController = navController)
        }
    }

}

