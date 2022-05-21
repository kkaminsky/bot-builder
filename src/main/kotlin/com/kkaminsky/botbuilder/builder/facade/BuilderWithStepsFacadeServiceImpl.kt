package com.kkaminsky.builderapi.builder.facade

import com.kkaminsky.builderapi.builder.StringStateMachineBuilder
import com.kkaminsky.builderapi.dto.step.StepWithEventsDto
import com.kkaminsky.builderapi.objects.State
import com.kkaminsky.builderapi.service.UserService
import com.kkaminsky.builderapi.service.facade.StepWithEventsFacadeService
import com.kkaminsky.builderapi.service.facade.TransitionWithStepsFacade
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.action.Action
import org.springframework.stereotype.Service
import java.util.*

@Service
class BuilderWithStepsFacadeServiceImpl(
    private val stepWithEventsFacadeService: StepWithEventsFacadeService,
    private val transitionWithStepsFacade: TransitionWithStepsFacade,
    private val stringStateMachineBuilder: StringStateMachineBuilder
) : BuilderWithStepsFacadeService {
    override fun buildStateMachineForUser(stateMachineId: UUID): StateMachine<String, String> {
        val steps = stepWithEventsFacadeService.getSteps(stateMachineId)
        val transitions = transitionWithStepsFacade.getTransitionsWithSteps(stateMachineId)
        stringStateMachineBuilder.build()
    }

    fun mapStepsToStates(steps: List<StepWithEventsDto>): List<State>{
        return steps.map {
            State(
                name = it.id.toString(),
                action = Action {  }
            )
        }
    }
}