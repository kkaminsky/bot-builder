package com.kkaminsky.builderapi.dto

import java.util.*

data class AddStepToTransitionDto(
    val stepId: UUID,
    val transitionId: UUID,
    val directionType: DirectionType
)