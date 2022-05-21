package com.kkaminsky.statemachineapi.transition

import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.common.annotation.AnnotationConfigurerBuilder

/**
 * Базовый интерфейс для конфигурирования состояний переходов между событиями
 */
interface TransitionConfig<State : Enum<State>,Event: Enum<Event>> {

    val fromState: State

    fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>)
    : AnnotationConfigurerBuilder<StateMachineTransitionConfigurer<State, Event>>

}