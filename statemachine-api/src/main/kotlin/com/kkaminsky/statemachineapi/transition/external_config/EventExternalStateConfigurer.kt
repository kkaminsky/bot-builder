package com.kkaminsky.statemachineapi.transition.external_config

import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.ExternalTransitionConfigurer

/**
 * Наследник базового элемента
 * добавляет описание вызываемого события
 */
interface EventExternalStateConfigurer<State:Enum<State>,Event: Enum<Event>>
    : TargetExternalStateConfigurer<State, Event> {

    val event : Event

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): ExternalTransitionConfigurer<State, Event> {
        return super.initializeConfig(baseConfigurer)
                .event(event)
    }
}