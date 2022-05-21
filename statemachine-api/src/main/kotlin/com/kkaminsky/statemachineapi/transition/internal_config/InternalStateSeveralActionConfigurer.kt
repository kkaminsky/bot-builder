package com.kkaminsky.statemachineapi.transition.internal_config


import com.kkaminsky.statemachineapi.transition.TransitionConfig
import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.common.annotation.AnnotationConfigurerBuilder

interface InternalStateSeveralActionConfigurer<State: Enum<State>, Event: Enum<Event>>:
    com.kkaminsky.statemachineapi.transition.TransitionConfig<State, Event> {

    val event: Event
    val actions: List<Action<State, Event>>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>): AnnotationConfigurerBuilder<StateMachineTransitionConfigurer<State, Event>> {
        var resultConfigurer = baseConfigurer
                .withInternal()
                .source(fromState)
                .event(event)
        actions.forEach {action->
            resultConfigurer = resultConfigurer.action(action)
        }
        return resultConfigurer
    }


}