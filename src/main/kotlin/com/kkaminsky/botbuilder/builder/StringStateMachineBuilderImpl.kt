package com.kkaminsky.botbuilder.builder

import com.kkaminsky.botbuilder.builder.objects.State
import com.kkaminsky.botbuilder.builder.objects.Transition
import com.kkaminsky.botbuilder.builder.objects.TransitionEvent
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineBuilder
import org.springframework.stereotype.Service

@Service
class StringStateMachineBuilderImpl : StringStateMachineBuilder {
    override fun build(transitions: List<Transition>, states: List<State>, startEvent: TransitionEvent,startState: State): StateMachine<String, String> {
        val builder =  StateMachineBuilder.builder<String, String>()
        builder.configureConfiguration()
            .withConfiguration()
            .autoStartup(true)


        val transitionConfigurer = builder.configureTransitions()

        transitions.forEach { transition ->
            transitionConfigurer
                .withExternal()
                .source(transition.fromStateName)
                .target(transition.toStateName)
                .event(transition.event.name)
                .and()
        }

        transitionConfigurer
            .withExternal()
            .source("start")
            .target(startState.name)
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