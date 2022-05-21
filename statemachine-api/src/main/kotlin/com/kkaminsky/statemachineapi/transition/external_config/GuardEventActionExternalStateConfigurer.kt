package com.kkaminsky.statemachineapi.transition.external_config

import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.ExternalTransitionConfigurer
import org.springframework.statemachine.guard.Guard

interface GuardEventActionExternalStateConfigurer<State:Enum<State>,Event:Enum<Event>>
    : EventSeveralActionExternalStateConfigurer<State, Event> {

    val guards: Collection<Guard<State,Event>>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): ExternalTransitionConfigurer<State, Event> {
        var machineStateMove = super.initializeConfig(baseConfigurer)
        guards.forEach {guard->
            machineStateMove = machineStateMove.guard(guard)
        }
        return machineStateMove
    }
}