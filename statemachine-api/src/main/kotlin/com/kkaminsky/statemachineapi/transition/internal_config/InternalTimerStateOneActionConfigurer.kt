package com.kkaminsky.statemachineapi.transition.internal_config

import com.kkaminsky.statemachineapi.transition.TransitionConfig
import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.common.annotation.AnnotationConfigurerBuilder

interface InternalTimerStateOneActionConfigurer<State: Enum<State>, Event: Enum<Event>> :
    com.kkaminsky.statemachineapi.transition.TransitionConfig<State, Event> {

    val action: Action<State, Event>
    val period: Long

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): AnnotationConfigurerBuilder<StateMachineTransitionConfigurer<State, Event>> {
        return baseConfigurer
                .withInternal()
                .source(fromState)
                .timerOnce(period)
                .action(action)
    }
}