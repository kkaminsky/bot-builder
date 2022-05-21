package com.kkaminsky.statemachineapi.transition.internal_config


import com.kkaminsky.statemachineapi.transition.TransitionConfig
import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.common.annotation.AnnotationConfigurerBuilder

interface InternalStateOneActionConfigurer<State: Enum<State>, Event: Enum<Event>> :
    TransitionConfig<State, Event> {

    val event: Event
    val action: Action<State, Event>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): AnnotationConfigurerBuilder<StateMachineTransitionConfigurer<State, Event>> {
        return baseConfigurer
                .withInternal()
                .source(fromState)
                .event(event)
                .action(action)
    }

}