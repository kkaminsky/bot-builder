package com.kkaminsky.builderapi.dto.statemachine

import com.kkaminsky.builderapi.dto.user.UserDto
import java.util.*

data class StateMachineDto(
    val id: UUID,
    val name: String,
    val user: UserDto
)