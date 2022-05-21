package com.kkaminsky.builderapi.dto.transition

import java.util.*

data class EditTransitionDto(
    val id: UUID,
    val fromStepId: UUID,
    val toStepId: UUID,
    val eventTypeIds: List<UUID>
)
