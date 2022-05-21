package com.kkaminsky.statemachineapi.transition.internal_config

import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.common.annotation.AnnotationConfigurerBuilder
import org.springframework.statemachine.guard.Guard

interface InternalStateSeveralActionGuardWithDefaultErrorActionConfigurer<State: Enum<State>, Event: Enum<Event>> :
    InternalStateSeveralActionConfigurer<State, Event> {
    val defaultErrorAction: Action<State, Event>
    val guard: Guard<State, Event>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): AnnotationConfigurerBuilder<StateMachineTransitionConfigurer<State, Event>> {
        var resultConfigurer = baseConfigurer
                .withInternal()
                .source(fromState)
                .guard(guard)
                .event(event)
        actions.forEach {action->
            resultConfigurer = resultConfigurer.action(action, defaultErrorAction)
        }
        return resultConfigurer
    }
}