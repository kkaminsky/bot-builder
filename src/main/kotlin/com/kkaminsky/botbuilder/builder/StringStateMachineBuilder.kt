package com.kkaminsky.builderapi.builder

import com.kkaminsky.builderapi.objects.State
import com.kkaminsky.builderapi.objects.Transition
import com.kkaminsky.builderapi.objects.TransitionEvent
import org.springframework.statemachine.StateMachine


interface StringStateMachineBuilder {
    fun build(transitions: List<Transition>, states: List<State>, startEvent: TransitionEvent): StateMachine<String, String>
}