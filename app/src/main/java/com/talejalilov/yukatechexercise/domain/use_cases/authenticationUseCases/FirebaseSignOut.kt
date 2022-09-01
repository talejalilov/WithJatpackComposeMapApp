package com.talejalilov.yukatechexercise.domain.use_cases.authenticationUseCases // ktlint-disable package-name

import com.talejalilov.yukatechexercise.domain.repo.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignOut@Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() = repository.firebaseSignOut()
}
