package com.kkaminsky.builderapi.dto

import java.util.*

data class CreateEventDto(
    val text: String,
    val stateMachineId: UUID,
    val eventTypeId: UUID
)
