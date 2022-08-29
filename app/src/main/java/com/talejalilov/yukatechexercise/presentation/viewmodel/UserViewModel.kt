package com.talejalilov.yukatechexercise.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.talejalilov.yukatechexercise.domain.model.User
import com.talejalilov.yukatechexercise.domain.use_cases.userUsecases.GetUserDataUseCase
import com.talejalilov.yukatechexercise.domain.use_cases.userUsecases.UserUseCases
import com.talejalilov.yukatechexercise.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val userId = auth.currentUser?.uid
    private val _getUserData = mutableStateOf<Response<User?>>(Response.Success(null))

    val getUserData: State<Response<User?>> = _getUserData

    val _setUserRoute = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val setUserRoute : State<Response<Boolean>> = _setUserRoute


    fun getUserInfo(adminId:String) {
        if (userId != null) {

            viewModelScope.launch {
                userUseCases.getUserDataUseCase(adminId = adminId, userId = userId).collect{
                    _getUserData.value = it
                }
            }
        }
    }

    fun setUserRoute(  adminId: String,
                       userId: String,
                       latitude :String,
                       longitude:String){
        if(userId!=null){
            viewModelScope.launch {
                userUseCases.setUserRouteUseCase(
                    adminId = adminId,
                    userId = userId,
                    latitude = latitude,
                    longitude = longitude).collect{
                        _setUserRoute.value = it
                }
            }

        }
    }
}