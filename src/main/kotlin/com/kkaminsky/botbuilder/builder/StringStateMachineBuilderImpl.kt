package com.kkaminsky.builderapi.builder

import com.kkaminsky.builderapi.objects.State
import com.kkaminsky.builderapi.objects.Transition
import com.kkaminsky.builderapi.objects.TransitionEvent
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineBuilder
import org.springframework.stereotype.Service

@Service
class StringStateMachineBuilderImpl : StringStateMachineBuilder {
    override fun build(transitions: List<Transition>, states: List<State>, startEvent: TransitionEvent): StateMachine<String, String> {
        val builder =  StateMachineBuilder.builder<String, String>()
        builder.configureConfiguration()
            .withConfiguration()
            .autoStartup(true)


        val transitionConfigurer = builder.configureTransitions()

        transitions.forEach { transition ->
            transitionConfigurer
                .withExternal()
                .source(transition.fromState.name)
                .target(transition.toState.name)
                .event(transition.event.name)
                .and()
        }

        transitionConfigurer
            .withExternal()
            .source("start")
            .target(states.first().name)
            .event(startEvent.name)

        val statesConfigurer = builder.configureStates()
            .withStates()
            .initial("start")
        states.forEach { state ->
            statesConfigurer
                .stateEntry(state.name,state.action)
        }

        return builder.build()
    }
}