package com.kkaminsky.builderapi.dto.step

import com.kkaminsky.builderapi.dto.DirectionType
import java.util.*

data class SetNewStepForTransitionDto(
    val newStepId: UUID,
    val transitionId: UUID,
    val directionType: DirectionType
)
