package com.talejalilov.yukatechexercise.domain.use_cases.authenticationUseCases // ktlint-disable package-name

data class AuthenticationUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val firebaseAuthState: FirebaseAuthState,
    val firebaseSignIn: FirebaseSignIn,
    val firebaseSignOut: FirebaseSignOut,
    val firebaseSignUp: FirebaseSignUp,
    val firebaseSignUpUser: FirebaseSignUpUsers


)
