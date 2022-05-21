package com.kkaminsky.builderapi.dto.step

import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import java.util.*

data class StepDto(
    val id: UUID,
    val text: String,
    val stateMachine: StateMachineDto
)