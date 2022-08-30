package com.talejalilov.yukatechexercise

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.maps.model.LatLng
import com.talejalilov.yukatechexercise.presentation.*
import com.talejalilov.yukatechexercise.presentation.Authentication.AdminLoginScreen
import com.talejalilov.yukatechexercise.presentation.Authentication.ChooseScreen
import com.talejalilov.yukatechexercise.presentation.Authentication.SignUpScreen
import com.talejalilov.yukatechexercise.presentation.Authentication.UserLoginScreen
import com.talejalilov.yukatechexercise.presentation.viewmodel.MainActivityViewModel
import com.talejalilov.yukatechexercise.ui.theme.YukaTechExerciseTheme
import com.talejalilov.yukatechexercise.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
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

    private fun getLocationPermission(){
        if (ContextCompat.checkSelfPermission(this.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            viewModel.permissionGrand(true)
            getDeviceLocation()

        }else{
            Log.d("Exception", "Permission not granted")
        }
    }

    private  fun getDeviceLocation(){
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        try {
            if (viewModel.locationPermissionGranted.value ==true){
                val locationResult = fusedLocationProviderClient.lastLocation

                locationResult.addOnCompleteListener {
                        task ->
                    if (task.isSuccessful){
                        val lastKnownLocation = task.result

                        if (lastKnownLocation != null){
                            viewModel.currentUserGeoCOord(
                                LatLng(
                                    lastKnownLocation.altitude,
                                    lastKnownLocation.longitude
                                )
                            )
                        }
                    }else{
                        Log.d("Exception"," Current User location is null")
                    }
                }

            }

        }catch (e: SecurityException){
            Log.d("Exception", "Exception:  $e.message.toString()")
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

