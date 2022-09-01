package com.talejalilov.yukatechexercise.domain.use_cases.authenticationUseCases // ktlint-disable package-name

import com.talejalilov.yukatechexercise.domain.repo.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignIn@Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String) = repository.firebaseSignIn(email, password)
}
