package com.kkaminsky.botbuilder.builder

import com.kkaminsky.botbuilder.builder.objects.State
import com.kkaminsky.botbuilder.builder.objects.Transition
import com.kkaminsky.botbuilder.builder.objects.TransitionEvent
import org.springframework.statemachine.StateMachine


interface StringStateMachineBuilder {
    fun build(transitions: List<Transition>, states: List<State>, startEvent: TransitionEvent, startState: State): StateMachine<String, String>
}