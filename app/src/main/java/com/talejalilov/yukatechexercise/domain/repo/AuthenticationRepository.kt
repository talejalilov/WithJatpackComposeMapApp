package com.talejalilov.yukatechexercise.domain.repo

import com.talejalilov.yukatechexercise.util.Response
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    fun isUserAuthenticatedInFirebase() : Boolean

    fun getFirebaseAuthState() : Flow<Boolean>

    fun firebaseSignIn(email:String,password:String) :Flow<Response<Boolean>>

    fun firebaseSignUp(username:String, email:String,password:String) :Flow<Response<Boolean>>

    fun firebaseSignUpUser(username:String, email:String,password:String) :Flow<Response<Boolean>>

    fun firebaseSignOut() :Flow<Response<Boolean>>


}