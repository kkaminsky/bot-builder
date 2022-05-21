package com.kkaminsky.builderapi.service.facade

import com.kkaminsky.builderapi.dto.step.CreateStepWithEventsDto
import com.kkaminsky.builderapi.dto.step.EditStepWithEventsDto
import com.kkaminsky.builderapi.dto.step.StepWithEventsDto
import java.util.*

interface StepWithEventsFacadeService {
    fun getSteps(stateMachineId: UUID): List<StepWithEventsDto>
    fun getStartStep(stateMachineId: UUID): StepWithEventsDto
    fun createStepWithEvents(model: CreateStepWithEventsDto): StepWithEventsDto
    fun editStepWithEvents(model: EditStepWithEventsDto): StepWithEventsDto
}