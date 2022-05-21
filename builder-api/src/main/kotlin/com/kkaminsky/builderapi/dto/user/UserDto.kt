package com.kkaminsky.builderapi.dto

import java.util.*

data class UserDto(
    val id: UUID,
    val username: String,
    val telegramId: String
)