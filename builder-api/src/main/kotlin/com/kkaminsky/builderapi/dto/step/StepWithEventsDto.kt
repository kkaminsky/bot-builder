package com.kkaminsky.builderapi.dto.step

import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import com.kkaminsky.builderapi.dto.event.EventDto
import java.util.*

data class StepWithEventsDto(
    val id: UUID,
    val text: String,
    val events: List<EventDto>,
    val stateMachine: StateMachineDto
)