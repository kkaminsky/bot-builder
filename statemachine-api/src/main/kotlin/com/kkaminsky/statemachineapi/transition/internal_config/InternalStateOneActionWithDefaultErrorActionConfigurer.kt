package com.kkaminsky.statemachineapi.transition.internal_config

import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.common.annotation.AnnotationConfigurerBuilder

interface InternalStateOneActionWithDefaultErrorActionConfigurer<State: Enum<State>, Event: Enum<Event>> :
    InternalStateOneActionConfigurer<State, Event> {

    val errorActionHandler: Action<State, Event>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): AnnotationConfigurerBuilder<StateMachineTransitionConfigurer<State, Event>> {
        return baseConfigurer
            .withInternal()
            .source(fromState)
            .event(event)
            .action(action,errorActionHandler)
    }
}