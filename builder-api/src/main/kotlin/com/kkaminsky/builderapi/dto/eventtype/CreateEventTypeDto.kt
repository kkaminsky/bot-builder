package com.kkaminsky.builderapi.dto.event

import java.util.*

data class CreateEventTypeDto(
    val name: String,
    val stateMachineId: UUID
)
