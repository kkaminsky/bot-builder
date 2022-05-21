package com.kkaminsky.statemachineapi.transition.choice_config

import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.ChoiceTransitionConfigurer
import org.springframework.statemachine.guard.Guard

interface ChooseExternalWithoutAction<State:Enum<State>,Event: Enum<Event>> :
    BaseChooseExternalStateConfigurer<State, Event> {

    val firstState: State
    val secondState: State
    val firstGuard: Guard<State, Event>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>)
            : ChoiceTransitionConfigurer<State, Event> {
        return baseConfigurer
                .withChoice()
                .source(fromState)
                .first(firstState,firstGuard)
                .last(secondState)
    }

}