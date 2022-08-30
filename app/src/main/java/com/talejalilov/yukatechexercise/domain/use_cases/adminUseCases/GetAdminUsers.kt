package com.talejalilov.yukatechexercise.domain.use_cases.adminUseCases

import com.talejalilov.yukatechexercise.domain.repo.AdminRepository
import com.talejalilov.yukatechexercise.domain.repo.UserRepository
import javax.inject.Inject

class GetAdminUsers @Inject constructor(
    private val repository: AdminRepository
) {
    operator fun invoke(adminId:String) = repository.getAdminUsers(adminId = adminId)

}