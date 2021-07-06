package com.kkaminsky.botbuilder.builder

import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineBuilder
import org.springframework.stereotype.Service

@Service
class StringStateMachineBuilderImpl : StringStateMachineBuilder {
    override fun build(): StateMachine<String, String> {
        val builder =  StateMachineBuilder.builder<String, String>()
        builder.configureConfiguration()
            .withConfiguration()
            .autoStartup(true)

        builder.configureTransitions()
            .withExternal()
            .source("S1")
            .target("S2")
            .event("E1")

        builder.configureStates()
            .withStates()
            .initial("S1")
            .stateEntry("S1"){ println("In S1!!")}
            .stateEntry("S2"){ println("In S2!!")}

        return builder.build()
    }
}