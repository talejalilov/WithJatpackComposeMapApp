package com.talejalilov.yukatechexercise.util

sealed class Screens(val route:String){

    object SplashScreen :Screens("splash_screen")
    object ChooseScreen :Screens("choose_screen")
    object AdminLoginScreen :Screens("admin_login_screen")
    object UserLoginScreen :Screens("user_login_screen")
    object SignUpScreen :Screens("signup_screen")
    object FeedScreen :Screens("feed_screen")
    object CreateUserAccount :Screens("create_users")


}
