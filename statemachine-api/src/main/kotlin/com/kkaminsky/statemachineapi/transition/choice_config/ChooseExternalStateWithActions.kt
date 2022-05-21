package com.kkaminsky.statemachineapi.transition.choice_config

import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.common.annotation.AnnotationConfigurerBuilder
import org.springframework.statemachine.guard.Guard


interface ChooseExternalStateWithActions<State: Enum<State>, Event:Enum<Event>> :
    com.kkaminsky.statemachineapi.transition.choice_config.BaseChooseExternalStateConfigurer<State, Event> {
    val firstState: State
    val secondState: State
    val firstAction: Action<State, Event>
    val secondAction: Action<State, Event>
    val guard: Guard<State, Event>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): AnnotationConfigurerBuilder<StateMachineTransitionConfigurer<State, Event>> {
        return baseConfigurer
            .withChoice()
            .source(fromState)
            .first(firstState,guard,firstAction)
            .last(secondState,secondAction)

    }
}