package com.kkaminsky.builderapi.dto

import java.util.*


data class TransitionDto(
    val id: UUID,
    val eventTypeId: UUID,
    val stateMachineId: UUID
)

data class TransitionFullDto(
    val transition: TransitionDto,
    val fromStep: StepDto,
    val toStep: StepDto,
    val event
)



