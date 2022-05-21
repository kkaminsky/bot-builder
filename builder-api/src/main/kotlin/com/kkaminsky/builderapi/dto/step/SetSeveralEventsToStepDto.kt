package com.kkaminsky.builderapi.dto.step

import java.util.*

data class SetSeveralEventsToStepDto(
    val stepId: UUID,
    val eventIds: List<UUID>
)