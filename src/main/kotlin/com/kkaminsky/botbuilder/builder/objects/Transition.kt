package com.kkaminsky.botbuilder.builder.objects

data class Transition(
    val fromStateName: String,
    val toStateName: String,
    val event: TransitionEvent
)