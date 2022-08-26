package com.talejalilov.yukatechexercise.domain.use_cases

import com.talejalilov.yukatechexercise.domain.repo.AuthenticationRepository
import javax.inject.Inject

class IsUserAuthenticated @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() = repository.isUserAuthenticatedInFirebase()
}