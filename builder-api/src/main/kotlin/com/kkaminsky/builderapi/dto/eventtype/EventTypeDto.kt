package com.kkaminsky.builderapi.dto.eventtype

import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import java.util.*

data class EventTypeDto(
    val id: UUID,
    val name: String,
    val stateMachine: StateMachineDto
)