package com.talejalilov.yukatechexercise.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talejalilov.yukatechexercise.domain.use_cases.authenticationUseCases.AuthenticationUseCases
import com.talejalilov.yukatechexercise.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val autUseCases : AuthenticationUseCases
) :ViewModel(){

    val isUserAuthenticated get() = autUseCases.isUserAuthenticated()

    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState : State<Response<Boolean>> = _signInState

    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState : State<Response<Boolean>> = _signUpState

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState : State<Response<Boolean>> = _signOutState

    private val _firebaseAuthState = mutableStateOf<Boolean>(false)
    val firebaseAuthState : State<Boolean> = _firebaseAuthState

    fun signOut(){
        viewModelScope.launch {
            autUseCases.firebaseSignOut().collect(){
                _signOutState.value = it

                if(it==Response.Success(true)){
                    _signOutState.value = Response.Success(false)

                }
            }
        }
    }

    fun signIn(email:String, password:String){
        viewModelScope.launch {
            autUseCases.firebaseSignIn(email = email, password = password).collect{
                _signInState.value = it
            }
        }
    }

    fun signUp(email:String, password:String, userName:String){
        viewModelScope.launch {
            autUseCases.firebaseSignUp(email = email, password = password, username = userName).collect{
                Log.d("ALT","SIGN UP")
                _signUpState.value = it
            }
        }
    }

    fun signUpUser(email:String, password:String, userName:String){
        viewModelScope.launch {
            autUseCases.firebaseSignUpUser(email = email, password = password, username = userName).collect{
                Log.d("ALT","SIGN UP")
                _signUpState.value = it
            }
        }
    }

    fun getFirebaseAuthState(){
        viewModelScope.launch {
            autUseCases.firebaseAuthState().collect{
                _firebaseAuthState.value = it
            }
        }
    }
}