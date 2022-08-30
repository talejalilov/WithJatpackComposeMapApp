package com.talejalilov.yukatechexercise.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.talejalilov.yukatechexercise.domain.model.User
import com.talejalilov.yukatechexercise.domain.use_cases.adminUseCases.AdminUseCases
import com.talejalilov.yukatechexercise.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val adminUseCases: AdminUseCases,
    private val auth :FirebaseAuth
) :ViewModel(){

    private val _adminUsers = mutableStateOf<Response<List<User>>>(Response.Loading)
    val adminUsers : State<Response<List<User>>> = _adminUsers


    fun getAdminUsers(){
        val adminId = auth.currentUser?.uid.toString()
        viewModelScope.launch {
            adminUseCases.getAdminsUsers(adminId= adminId).collect{
                _adminUsers.value = it
            }
        }
    }
}