package com.kkaminsky.builderapi.dto.transition

import com.kkaminsky.builderapi.dto.eventtype.EventTypeDto
import com.kkaminsky.builderapi.dto.step.StepDto
import java.util.*


data class TransitionWithStepsAndEventsDto(
    val id: UUID,
    val stateMachineId: UUID,//TODO: remove?
    val fromStep: StepDto,
    val toStep: StepDto,
    val eventTypes: List<EventTypeDto>
)