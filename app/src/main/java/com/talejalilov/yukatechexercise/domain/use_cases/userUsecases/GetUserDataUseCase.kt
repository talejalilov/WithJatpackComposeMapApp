package com.talejalilov.yukatechexercise.domain.use_cases.userUsecases

import com.talejalilov.yukatechexercise.domain.repo.UserRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(adminId:String,userId:String) = repository.getUserData(adminId = adminId, userId = userId)


}