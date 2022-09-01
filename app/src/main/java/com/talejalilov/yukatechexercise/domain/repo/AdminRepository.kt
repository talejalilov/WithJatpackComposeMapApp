package com.talejalilov.yukatechexercise.domain.repo

import com.talejalilov.yukatechexercise.data.model.User
import com.talejalilov.yukatechexercise.util.Response
import kotlinx.coroutines.flow.Flow

interface AdminRepository {

    fun getAdminUsers(adminId: String): Flow<Response<List<User>>>
}
