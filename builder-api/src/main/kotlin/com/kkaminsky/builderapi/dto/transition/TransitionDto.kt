package com.kkaminsky.builderapi.dto.transition

import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import java.util.*


data class TransitionDto(
    val id: UUID,
    val stateMachine: StateMachineDto
)



