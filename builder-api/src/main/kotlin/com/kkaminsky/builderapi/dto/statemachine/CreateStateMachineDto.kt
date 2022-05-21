package com.kkaminsky.builderapi.dto.statemachine

import java.util.*

data class CreateStateMachineDto(
    val userId: UUID,
    val name: String
)
