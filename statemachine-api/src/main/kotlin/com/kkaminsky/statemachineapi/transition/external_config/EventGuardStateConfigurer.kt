package com.kkaminsky.statemachineapi.transition.external_config

import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.ExternalTransitionConfigurer
import org.springframework.statemachine.guard.Guard

interface EventGuardStateConfigurer<State:Enum<State>,Event: Enum<Event>> :
    com.kkaminsky.statemachineapi.transition.external_config.EventExternalStateConfigurer<State, Event> {

    val guard: Guard<State, Event>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): ExternalTransitionConfigurer<State, Event> {
        return super.initializeConfig(baseConfigurer)
                .guard(guard)
    }
}