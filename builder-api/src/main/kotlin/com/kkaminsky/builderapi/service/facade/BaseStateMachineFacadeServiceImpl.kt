package com.kkaminsky.builderapi.service.facade

import com.kkaminsky.builderapi.dto.eventtype.CreateEventTypeWithEventDto
import com.kkaminsky.builderapi.dto.statemachine.CreateStateMachineDto
import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import com.kkaminsky.builderapi.dto.step.CreateStepWithEventsDto
import com.kkaminsky.builderapi.dto.transition.CreateTransitionWithStepsDto
import com.kkaminsky.builderapi.service.StateMachineService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BaseStateMachineFacadeServiceImpl(
    private val stateMachineService: StateMachineService,
    private val eventWithEventTypeFacadeService: EventWithEventTypeFacadeService,
    private val stepWithEventsFacadeService: StepWithEventsFacadeService,
    private val transitionWithStepsFacade: TransitionWithStepsFacade
) : BaseStateMachineFacadeService {

    @Transactional
    override fun createBaseStatemachine(userId: UUID): StateMachineDto {
        val sm = stateMachineService.createStateMachine(CreateStateMachineDto(
            userId = userId,
            name = "Dialog 1"
        ))


        /*val fineMoodEventType = eventWithEventTypeFacadeService.createEventTypeWithEventsDto(CreateEventTypeWithEventDto(
            name = "Fine buttons",
            stateMachineId = sm.id,
            eventTexts = listOf("Excellent", "Fine", "All in order")
        ))*/

        val goodMoodEventType = eventWithEventTypeFacadeService.createEventTypeWithEventsDto(CreateEventTypeWithEventDto(
            name = "Good buttons",
            stateMachineId = sm.id,
            eventTexts = listOf("Good")
        ))

        val badMoodEventType = eventWithEventTypeFacadeService.createEventTypeWithEventsDto(CreateEventTypeWithEventDto(
            name = "Bad buttons",
            stateMachineId = sm.id,
            eventTexts = listOf("Not good")
        ))


       /* val positiveEventType = eventWithEventTypeFacadeService.createEventTypeWithEventsDto(CreateEventTypeWithEventDto(
            name = "Yes button",
            stateMachineId = sm.id,
            eventTexts = listOf("Yes")
        ))

        val negativeEventType = eventWithEventTypeFacadeService.createEventTypeWithEventsDto(CreateEventTypeWithEventDto(
            name = "No button",
            stateMachineId = sm.id,
            eventTexts = listOf("No")
        ))*/

        val firstStep = stepWithEventsFacadeService.createStepWithEvents(CreateStepWithEventsDto(
            isStart = true,
            text = "How are you?",
            stateMachineId = sm.id,
            eventIds = listOf(goodMoodEventType,badMoodEventType).flatMap { it.events.map { it.id } }
        ))

        val goodStep = stepWithEventsFacadeService.createStepWithEvents(CreateStepWithEventsDto(
            text = "I'm happy for you!",
            stateMachineId = sm.id,
            eventIds = listOf()
        ))

        val notGoodStep = stepWithEventsFacadeService.createStepWithEvents(CreateStepWithEventsDto(
            text = "Can I help you?",
            stateMachineId = sm.id,
            eventIds = listOf()
        ))

       /* val badStep = stepWithEventsFacadeService.createStepWithEvents(CreateStepWithEventsDto(
            text = "What's happened?",
            stateMachineId = sm.id,
            eventIds = listOf()
        ))


        val writeYourWishStep = stepWithEventsFacadeService.createStepWithEvents(CreateStepWithEventsDto(
            text = "Write your wish and I'll do it!",
            stateMachineId = sm.id,
            eventIds = listOf()
        ))

        val writeMeIfYouWantStep = stepWithEventsFacadeService.createStepWithEvents(CreateStepWithEventsDto(
            text = "Ok, if you want something, call me!",
            stateMachineId = sm.id,
            eventIds = listOf()
        ))*/

        transitionWithStepsFacade.createTransitionWithSteps(CreateTransitionWithStepsDto(
            fromStep = firstStep.id,
            toStep = goodStep.id,
            eventTypeIds = listOf(goodMoodEventType.id),
            stateMachineId = sm.id
        ))

        transitionWithStepsFacade.createTransitionWithSteps(CreateTransitionWithStepsDto(
            fromStep = firstStep.id,
            toStep = notGoodStep.id,
            eventTypeIds = listOf(badMoodEventType.id),
            stateMachineId = sm.id
        ))

        /*transitionWithStepsFacade.createTransitionWithSteps(CreateTransitionWithStepsDto(
            fromStep = firstStep.id,
            toStep = badStep.id,
            eventTypeIds = listOf(badMoodEventType.id),
            stateMachineId = sm.id
        ))

        transitionWithStepsFacade.createTransitionWithSteps(CreateTransitionWithStepsDto(
            fromStep = goodStep.id,
            toStep = writeMeIfYouWantStep.id,
            eventTypeIds = listOf(negativeEventType.id),
            stateMachineId = sm.id
        ))

        transitionWithStepsFacade.createTransitionWithSteps(CreateTransitionWithStepsDto(
            fromStep = goodStep.id,
            toStep = writeYourWishStep.id,
            eventTypeIds = listOf(positiveEventType.id),
            stateMachineId = sm.id
        ))*/


        return sm
    }
}