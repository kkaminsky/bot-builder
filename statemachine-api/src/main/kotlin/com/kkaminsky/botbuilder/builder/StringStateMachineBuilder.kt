package com.kkaminsky.botbuilder.builder

import org.springframework.statemachine.StateMachine

interface StringStateMachineBuilder {
    fun build(): StateMachine<String,String>
}