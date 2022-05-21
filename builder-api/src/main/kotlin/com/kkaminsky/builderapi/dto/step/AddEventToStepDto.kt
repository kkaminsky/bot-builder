package com.kkaminsky.builderapi.dto.step

import java.util.*

data class AddEventToStepDto(
    val stepId: UUID,
    val eventId: UUID
)

