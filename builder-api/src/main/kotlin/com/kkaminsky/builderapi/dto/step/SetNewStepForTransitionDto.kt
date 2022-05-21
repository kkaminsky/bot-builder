package com.kkaminsky.builderapi.dto.step

import com.kkaminsky.builderapi.dto.DirectionType
import java.util.*

data class DeleteStepFromTransitionDto(
    val stepId: UUID,
    val transitionId: UUID,
    val directionType: DirectionType
)
