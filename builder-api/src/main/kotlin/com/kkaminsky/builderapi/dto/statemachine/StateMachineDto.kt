package com.kkaminsky.builderapi.dto

import java.util.*

data class StateMachineDto(
    val id: UUID,
    val name: String,
    val user: UserDto
)