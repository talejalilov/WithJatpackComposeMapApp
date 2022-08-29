package com.talejalilov.yukatechexercise.domain.use_cases

import com.talejalilov.yukatechexercise.domain.repo.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignUpUsers@Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email:String, password :String, username:String) = repository.firebaseSignUpUser(email, password,username)

}