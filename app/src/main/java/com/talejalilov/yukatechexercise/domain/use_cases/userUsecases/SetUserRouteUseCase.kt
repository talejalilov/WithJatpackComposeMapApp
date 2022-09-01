package com.talejalilov.yukatechexercise.domain.use_cases.userUsecases // ktlint-disable package-name

import com.talejalilov.yukatechexercise.domain.repo.UserRepository
import javax.inject.Inject

class SetUserRouteUseCase @Inject constructor(
    private val repository: UserRepository
) {
//    operator fun invoke(adminId: String,
//                        userId: String,
//                        latitude :String,
//                        longitude:String) =
//        repository.setUserRoute(adminId = adminId, userId = userId, latitude = latitude, longitude = longitude)
//
}
