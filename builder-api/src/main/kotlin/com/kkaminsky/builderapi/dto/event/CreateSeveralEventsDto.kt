package com.kkaminsky.builderapi.dto.event

import java.util.*

data class CreateSeveralEventsDto(
    val texts: List<String>,
    val stateMachineId: UUID,
    val eventTypeId: UUID
)