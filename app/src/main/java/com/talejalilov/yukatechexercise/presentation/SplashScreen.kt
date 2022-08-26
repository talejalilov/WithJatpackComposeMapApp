package com.talejalilov.yukatechexercise.presentation

import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.talejalilov.yukatechexercise.R
import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.talejalilov.yukatechexercise.domain.use_cases.IsUserAuthenticated
import com.talejalilov.yukatechexercise.presentation.Authentication.AuthenticationViewModel

@Composable
fun SplashScreen(navController: NavController,
                 authViewModel: AuthenticationViewModel) {

    val authValue = authViewModel.isUserAuthenticated
    val scale = remember{
        Animatable(0f)
    }

    LaunchedEffect(key1 = true){

    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_yuka),
            contentDescription = "Splash Screen Logo", modifier = Modifier.scale(scale.value)
        )

    }
}