package com.kkaminsky.statemachineapi.transition.external_config

import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.ExternalTransitionConfigurer

interface EventSeveralActionExternalStateConfigurer<State:Enum<State>, Event: Enum<Event>>
    : EventExternalStateConfigurer<State, Event> {

    val actions: Collection<Action<State,Event>>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): ExternalTransitionConfigurer<State, Event> {
        var stateMachineMove = super.initializeConfig(baseConfigurer)
        actions.forEach{action->
            stateMachineMove = stateMachineMove.action(action)
        }
        return stateMachineMove
    }
}