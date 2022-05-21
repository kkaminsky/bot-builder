package com.kkaminsky.builderapi.dto.step

import java.util.*

data class EditStepWithEventsDto(
    val stepId: UUID,
    val text: String,
    val eventIds: List<UUID>
)