package com.talejalilov.yukatechexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.talejalilov.yukatechexercise.presentation.*
import com.talejalilov.yukatechexercise.presentation.Authentication.AdminLoginScreen
import com.talejalilov.yukatechexercise.presentation.Authentication.ChooseScreen
import com.talejalilov.yukatechexercise.presentation.Authentication.SignUpScreen
import com.talejalilov.yukatechexercise.presentation.Authentication.UserLoginScreen
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

//            //Klavyenin view-ları bozmamamsı için
//            ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view,inserts ->
//                val bottom  = inserts.getInsets(WindowInsetsCompat.Type.ime()).bottom
//                view.updatePadding(bottom = bottom)
//                inserts
//            }
        }
    }
}

@Composable
fun MapApplication(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        
        composable(route = Screens.ChooseScreen.route){
            ChooseScreen(navHostController = navController)
        }
        
        composable(route = Screens.UserLoginScreen.route){
            UserLoginScreen(navHostController = navController)
        }
        
        composable(route = Screens.AdminLoginScreen.route) {
            AdminLoginScreen(navHostController = navController)
        }

        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navHostController = navController)

        }

        composable(route = Screens.FeedScreen.route) {
            FeedScreen(navController = navController)
        }

        composable(route = Screens.CreateUserAccount.route) {
            CreateUserAccount(navController = navController)
        }
    }

}

