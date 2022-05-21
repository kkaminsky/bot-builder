package com.kkaminsky.builderapi.dto.step

import java.util.*

data class CreateStepsWithEventsDto(
    val text: String,
    val stateMachineId: UUID,
    val eventIds: List<UUID>
)