package com.kkaminsky.builderapi.dto.eventtype

import java.util.*

data class AddSeveralEventTypeToTransitionDto(
    val eventTypeIds: List<UUID>,
    val transitionId: UUID
)