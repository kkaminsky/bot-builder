package com.kkaminsky.builderapi.service.facade

import com.kkaminsky.builderapi.dto.step.StepDto
import com.kkaminsky.builderapi.dto.transition.CreateTransitionWithStepsDto
import com.kkaminsky.builderapi.dto.transition.EditTransitionDto
import com.kkaminsky.builderapi.dto.transition.TransitionWithStepsAndEventsDto
import java.util.*

interface TransitionWithStepsFacade {
    fun createTransitionWithSteps(model: CreateTransitionWithStepsDto): TransitionWithStepsAndEventsDto
    fun getTransitionsWithSteps(stateMachineId: UUID): List<TransitionWithStepsAndEventsDto>
    fun editTransitionWithSteps(model: EditTransitionDto): TransitionWithStepsAndEventsDto
}