package com.kkaminsky.builderapi.service.facade

import com.kkaminsky.builderapi.dto.eventtype.AddSeveralEventTypeToTransitionDto
import com.kkaminsky.builderapi.dto.DirectionType
import com.kkaminsky.builderapi.dto.eventtype.SetEventTypeToTransitionDto
import com.kkaminsky.builderapi.dto.step.SetNewStepForTransitionDto
import com.kkaminsky.builderapi.dto.transition.*
import com.kkaminsky.builderapi.service.EventService
import com.kkaminsky.builderapi.service.EventTypeService
import com.kkaminsky.builderapi.service.StepService
import com.kkaminsky.builderapi.service.TransitionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TransitionWithStepsFacadeImpl(
    private val transitionService: TransitionService,
    private val eventTypeService: EventTypeService,
    private val eventService: EventService,
    private val stepService: StepService
) : TransitionWithStepsFacade {

    @Transactional
    override fun createTransitionWithSteps(model: CreateTransitionWithStepsDto): TransitionWithStepsAndEventsDto {
        val transition = transitionService.createTransition(
            CreateTransitionDto(
                stateMachineId = model.stateMachineId
            )
        )

        val events = eventTypeService.addSeveralEventTypeToTransition(
            AddSeveralEventTypeToTransitionDto(
                eventTypeIds = model.eventTypeIds,
                transitionId = transition.id
            )
        )

        val fromStep = stepService.addStepToTransition(
            AddStepToTransitionDto(
                stepId = model.fromStep,
                transitionId = transition.id,
                directionType = DirectionType.START
            )
        )

        val toStep = stepService.addStepToTransition(
            AddStepToTransitionDto(
                stepId = model.toStep,
                transitionId = transition.id,
                directionType = DirectionType.FINISH
            )
        )

        return TransitionWithStepsAndEventsDto(
            id = transition.id,
            stateMachineId = transition.stateMachine.id,
            fromStep = fromStep,
            toStep = toStep,
            eventTypes = events
        )
    }


    @Transactional(readOnly = true)
    override fun getTransitionsWithSteps(stateMachineId: UUID): List<TransitionWithStepsAndEventsDto> {
        val transitions = transitionService.getTransitions(stateMachineId)

        val steps = stepService.getStepsByTransition(transitions.map { it.id })

        val eventTypes = eventTypeService.getEventTypesByTransitions(transitions.map { it.id })

        return transitions.map { transition ->
            val fromStep = steps[transition.id]?.find { it.second == DirectionType.START }?.first
                ?: throw Exception("It will be filtered in future!")
            val toStep = steps[transition.id]?.find { it.second == DirectionType.FINISH }?.first
                ?: throw Exception("It will be filtered in future!")//TODO

            TransitionWithStepsAndEventsDto(
                id = transition.id,
                stateMachineId = transition.stateMachine.id,
                fromStep = fromStep,
                toStep = toStep,
                eventTypes = eventTypes[transition.id] ?: listOf()
            )
        }
    }

    @Transactional
    override fun editTransitionWithSteps(model: EditTransitionDto): TransitionWithStepsAndEventsDto {
        val newFromStep = stepService.setNewStepForTransition(
            SetNewStepForTransitionDto(
                newStepId = model.fromStepId,
                transitionId = model.id,
                directionType = DirectionType.START
            )
        )

        val newToStep = stepService.setNewStepForTransition(
            SetNewStepForTransitionDto(
                newStepId = model.toStepId,
                transitionId = model.id,
                directionType = DirectionType.FINISH
            )
        )

        val eventTypes = eventTypeService.setEventTypesForTransition(SetEventTypeToTransitionDto(
            transitionId = model.id,
            newEventTypeIds = model.eventTypeIds
        ))

        return TransitionWithStepsAndEventsDto(
            id = model.id,
            stateMachineId = newFromStep.stateMachine.id,//TODO
            fromStep = newFromStep,
            toStep = newToStep,
            eventTypes = eventTypes
        )
    }
}