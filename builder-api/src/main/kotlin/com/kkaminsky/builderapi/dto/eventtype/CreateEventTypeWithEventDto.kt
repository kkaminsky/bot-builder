package com.kkaminsky.builderapi.dto.eventtype

import java.util.*

data class CreateEventTypeWithEventDto(
    val name: String,
    val stateMachineId: UUID,
    val eventTexts: List<String>
)