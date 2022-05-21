package com.kkaminsky.builderapi.dto.eventtype

import java.util.*

data class AddEventTypeToTransitionDto(
    val eventTypeId: UUID,
    val transitionId: UUID
)