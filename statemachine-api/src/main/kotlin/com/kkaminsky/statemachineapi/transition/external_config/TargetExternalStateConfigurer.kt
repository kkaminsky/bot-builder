package com.kkaminsky.statemachineapi.transition.external_config

import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.ExternalTransitionConfigurer

interface TargetExternalStateConfigurer <State:Enum<State>,Event:Enum<Event>> :
    ExternalStateConfigurer<State, Event> {

    val toState: State

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): ExternalTransitionConfigurer<State, Event> {
        return baseConfigurer
                .withExternal()
                .source(fromState)
                .target(toState)
    }
}