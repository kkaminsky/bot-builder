package com.kkaminsky.builderapi.dto

import java.util.*

data class AddEventToStepDto(
    val stepId: UUID,
    val eventId: UUID
)
