package com.kkaminsky.builderapi.dto.eventtype

import com.kkaminsky.builderapi.dto.event.EventDto
import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import java.util.*

data class EventTypeWithEventsDto(
    val id: UUID,
    val name: String,
    val stateMachine: StateMachineDto,
    val events: List<EventDto>
)