package com.kkaminsky.statemachineapi.transition.external_config

import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.ExternalTransitionConfigurer

interface EventActionExternalStateConfigurer<State:Enum<State>,Event: Enum<Event>>
    : com.kkaminsky.statemachineapi.transition.external_config.EventExternalStateConfigurer<State, Event> {

    val action: Action<State, Event>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): ExternalTransitionConfigurer<State, Event> {
        return super.initializeConfig(baseConfigurer)
                .action(action)
    }
}