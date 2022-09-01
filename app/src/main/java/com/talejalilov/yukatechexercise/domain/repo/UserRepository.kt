package com.talejalilov.yukatechexercise.domain.repo

import com.talejalilov.yukatechexercise.data.model.User
import com.talejalilov.yukatechexercise.util.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserData(adminId: String, userId: String): Flow<Response<User>>

//    fun getUserDetail(userId:String): Flow<Response<User>>
//
//    fun setUserRoute(
//        adminId: String,
//        userId: String,
//        latitude :String,
//        longitude:String
//    ):Flow<Response<Boolean>>
}
