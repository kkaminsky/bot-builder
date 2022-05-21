package com.kkaminsky.builderapi.dto

import java.util.*

data class CreateStepDto(
    val text: String,
    val stateMachineId: UUID
)
