package com.kkaminsky.builderapi.dto

import java.util.*

data class AddEventTypeToTransitionDto(
    val eventTypeId: UUID,
    val transitionId: UUID
)