package com.kkaminsky.botbuilder.builder.facade

import com.fasterxml.jackson.databind.ObjectMapper
import com.kkaminsky.botbuilder.builder.StringStateMachineBuilder
import com.kkaminsky.botbuilder.corestatemachine.action.CustomAction
import com.kkaminsky.botbuilder.builder.objects.*
import com.kkaminsky.builderapi.dto.step.StepWithEventsDto
import com.kkaminsky.builderapi.dto.transition.TransitionWithStepsAndEventsDto
import com.kkaminsky.builderapi.service.facade.StepWithEventsFacadeService
import com.kkaminsky.builderapi.service.facade.TransitionWithStepsFacade
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.statemachine.StateMachine
import org.springframework.stereotype.Service
import java.util.*

@Service
class BuilderWithStepsFacadeServiceImpl(
    private val stepWithEventsFacadeService: StepWithEventsFacadeService,
    private val transitionWithStepsFacade: TransitionWithStepsFacade,
    private val stringStateMachineBuilder: StringStateMachineBuilder,
    private val objectMapper: ObjectMapper,
    private val rabbitTemplate: RabbitTemplate
) : BuilderWithStepsFacadeService {
    override fun buildStateMachine(stateMachineId: UUID): StateMachine<String, String> {
        val steps = stepWithEventsFacadeService.getSteps(stateMachineId)
        val transitions = transitionWithStepsFacade.getTransitionsWithSteps(stateMachineId)
        val startStep = stepWithEventsFacadeService.getStartStep(stateMachineId)
        return stringStateMachineBuilder.build(
            states = steps.map(::mapStep),
            transitions = transitions.flatMap { mapTransition(it) },
            startEvent = StartDialog,
            startState = mapStep(startStep)
        )
    }

    fun mapStep(step: StepWithEventsDto): State{
        return State(
                name = step.id.toString(),
                action = CustomAction(
                    step = step,
                    objectMapper = objectMapper,
                    rabbitTemplate = rabbitTemplate))
    }

    fun mapTransition(transition: TransitionWithStepsAndEventsDto): List<Transition>{
        return transition.eventTypes.map { eventType ->
            Transition(
                fromStateName = transition.fromStep.id.toString(),
                toStateName = transition.toStep.id.toString(),
                event = CustomEvent(eventType.id.toString())
            )
        }
    }
}