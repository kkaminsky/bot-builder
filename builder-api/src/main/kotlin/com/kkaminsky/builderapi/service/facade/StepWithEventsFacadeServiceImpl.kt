package com.kkaminsky.builderapi.service.facade

import com.kkaminsky.builderapi.dto.step.*
import com.kkaminsky.builderapi.mapper.StepMapper
import com.kkaminsky.builderapi.service.EventService
import com.kkaminsky.builderapi.service.StateMachineService
import com.kkaminsky.builderapi.service.StepService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class StepWithEventsFacadeServiceImpl(
    private val stepService: StepService,
    private val eventService: EventService,
    private val stateMachineService: StateMachineService,
    private val stepMapper: StepMapper
) : StepWithEventsFacadeService {

    @Transactional(readOnly = true)
    override fun getSteps(stateMachineId: UUID): List<StepWithEventsDto> {
        val stateMachine = stateMachineService.getStateMachine(stateMachineId)
        val steps = stepService.getAllSteps(stateMachine.id)
        val events = eventService.getEventsForSteps(steps.map { it.id })
        return steps.map { stepMapper.map(it, events[it.id] ?: listOf()) }
    }


    @Transactional(readOnly = true)
    override fun getStartStep(stateMachineId: UUID): StepWithEventsDto {
        val step = stepService.getStartStep(stateMachineId)
        val events = eventService.getEventsForStep(step.id)
        return stepMapper.map(step,events)
    }


    @Transactional
    override fun createStepWithEvents(model: CreateStepWithEventsDto): StepWithEventsDto {
        val step = stepService.createStep(
            CreateStepDto(
                stateMachineId = model.stateMachineId,
                text = model.text,
                isStart = model.isStart
            )
        )

        val events = eventService.setSeveralEventsToStep(
            SetSeveralEventsToStepDto(
                stepId = step.id,
                eventIds = model.eventIds
            )
        )

        return StepWithEventsDto(
            id = step.id,
            stateMachine = step.stateMachine,
            text = step.text,
            events = events
        )
    }


    @Transactional
    override fun editStepWithEvents(model: EditStepWithEventsDto): StepWithEventsDto {
        val step = stepService.editStep(
            EditStepDto(
                id = model.stepId,
                newText = model.text
            )
        )


        val events = eventService.setSeveralEventsToStep(
            SetSeveralEventsToStepDto(
                stepId = step.id,
                eventIds = model.eventIds
            )
        )

        return StepWithEventsDto(
            id = step.id,
            stateMachine = step.stateMachine,
            text = step.text,
            events = events
        )
    }
}