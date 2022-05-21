package com.kkaminsky.builderapi.dto

import java.util.*

data class StepDto(
    val id: UUID,
    val text: String,
    val stateMachine: StateMachineDto
)