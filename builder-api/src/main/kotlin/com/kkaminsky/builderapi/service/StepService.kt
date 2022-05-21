package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.DirectionType
import com.kkaminsky.builderapi.dto.step.*
import com.kkaminsky.builderapi.dto.transition.AddStepToTransitionDto
import java.util.*

interface StepService {
    fun getStep(stepId: UUID): StepDto
    fun editStep(model: EditStepDto): StepDto
    fun getAllSteps(stateMachineId: UUID): List<StepDto>
    fun getStartStep(stateMachineId: UUID): StepDto
    fun getStepsByTransition(transitionIds: List<UUID>): Map<UUID, List<Pair<StepDto, DirectionType>>>
    fun createStep(createStepDto: CreateStepDto): StepDto
    fun addStepToTransition(model: AddStepToTransitionDto): StepDto
    fun setNewStepForTransition(model: SetNewStepForTransitionDto): StepDto
    fun getConnectedSteps(stepId: UUID): List<StepWithEventsDto>
}