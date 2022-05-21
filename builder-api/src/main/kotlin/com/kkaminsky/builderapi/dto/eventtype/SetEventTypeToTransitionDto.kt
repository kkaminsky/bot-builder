package com.kkaminsky.builderapi.dto.eventtype

import java.util.*

data class SetEventTypeToTransitionDto(
    val transitionId: UUID,
    val newEventTypeIds: List<UUID>
)
