package com.kkaminsky.builderapi.dto.step

import java.util.*

data class CreateStepWithEventsDto(
    val text: String,
    val stateMachineId: UUID,
    val eventIds: List<UUID>,
    val isStart: Boolean = false
)