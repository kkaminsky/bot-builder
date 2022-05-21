package com.kkaminsky.statemachineapi.transition.internal_config

import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.common.annotation.AnnotationConfigurerBuilder
import org.springframework.statemachine.guard.Guard

interface InternalStateOneActionGuardWithDefaultErrorActionConfigurer<State: Enum<State>, Event: Enum<Event>> :
    InternalStateOneActionConfigurer<State, Event> {
    val defaultErrorAction: Action<State, Event>
    val guard: Guard<State, Event>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): AnnotationConfigurerBuilder<StateMachineTransitionConfigurer<State, Event>> {
        return baseConfigurer
                .withInternal()
                .source(fromState)
                .guard(guard)
                .event(event)
                .action(action,defaultErrorAction)
    }
}