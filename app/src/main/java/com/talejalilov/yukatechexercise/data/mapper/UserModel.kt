package com.talejalilov.yukatechexercise.data.mapper

import com.talejalilov.yukatechexercise.data.model.User
import com.talejalilov.yukatechexercise.data.model.dto.UserDto

fun User.toDto() = UserDto(
    username = username
)
