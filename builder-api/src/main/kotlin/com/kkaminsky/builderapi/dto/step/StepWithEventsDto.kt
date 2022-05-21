package com.kkaminsky.builderapi.dto

import java.util.*

data class StepWithEventsDto(
    val id: UUID,
    val text: String,
    val events: List<EventDto>,
    val stateMachine: StateMachineDto
)