package com.kkaminsky.builderapi.dto

import java.util.*

data class EventDto(
    val id: UUID,
    val text: String,
    val eventType: EventTypeDto,
    val stateMachine: StateMachineDto,
)