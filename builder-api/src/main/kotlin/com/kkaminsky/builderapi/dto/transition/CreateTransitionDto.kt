package com.kkaminsky.builderapi.dto

import java.util.*

data class CreateTransitionDto(
    val eventTypeId: UUID,
    val stateMachineId: UUID
)
