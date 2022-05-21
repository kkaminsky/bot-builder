package com.kkaminsky.statemachineapi.transition.internal_config

import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.common.annotation.AnnotationConfigurerBuilder

interface InternalStateSeveralActionWithDefaultErrorActionConfigurer<State: Enum<State>, Event: Enum<Event>> :
    InternalStateSeveralActionConfigurer<State, Event> {

    val defaultErrorAction: Action<State, Event>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): AnnotationConfigurerBuilder<StateMachineTransitionConfigurer<State, Event>> {
        var resultConfigurer = baseConfigurer
            .withInternal()
            .source(fromState)
            .event(event)
        actions.forEach {action->
            resultConfigurer = resultConfigurer.action(action, defaultErrorAction)
        }
        return resultConfigurer
    }
}