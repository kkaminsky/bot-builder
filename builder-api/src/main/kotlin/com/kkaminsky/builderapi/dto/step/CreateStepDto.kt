package com.kkaminsky.builderapi.dto.step

import java.util.*

data class CreateStepDto(
    val text: String,
    val stateMachineId: UUID,
    val isStart: Boolean = false
)