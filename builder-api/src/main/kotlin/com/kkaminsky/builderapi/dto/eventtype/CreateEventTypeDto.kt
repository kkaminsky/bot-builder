package com.kkaminsky.builderapi.dto.eventtype

import java.util.*

data class CreateEventTypeDto(
    val name: String,
    val stateMachineId: UUID
)