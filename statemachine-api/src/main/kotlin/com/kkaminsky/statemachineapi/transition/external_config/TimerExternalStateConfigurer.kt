package com.kkaminsky.statemachineapi.transition.external_config

import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.ExternalTransitionConfigurer

interface TimerExternalStateConfigurer<State:Enum<State>,Event: Enum<Event>>:
    TargetExternalStateConfigurer<State, Event> {

    val period: Long

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): ExternalTransitionConfigurer<State, Event> {
        return super.initializeConfig(baseConfigurer).timer(period)
    }
}