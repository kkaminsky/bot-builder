package com.kkaminsky.builderapi.dto.transition

import com.kkaminsky.builderapi.dto.step.StepDto
import java.util.*

data class CreateTransitionWithStepsDto(
    val eventTypeIds: List<UUID>,
    val fromStep: UUID,
    val toStep: UUID,
    val stateMachineId: UUID
)
