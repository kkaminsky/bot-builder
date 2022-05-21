package com.kkaminsky.builderapi.dto.transition

import com.kkaminsky.builderapi.dto.DirectionType
import java.util.*

data class AddStepToTransitionDto(
    val stepId: UUID,
    val transitionId: UUID,
    val directionType: DirectionType
)