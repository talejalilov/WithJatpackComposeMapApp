package com.talejalilov.yukatechexercise.presentation.Authentication

import androidx.lifecycle.ViewModel
import com.talejalilov.yukatechexercise.domain.use_cases.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val autUseCases :AuthenticationUseCases
) :ViewModel(){

    val isUserAuthenticated get() = autUseCases.isUserAuthenticated()


}