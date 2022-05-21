package com.kkaminsky.botbuilder.objects

data class Transition(
    val fromStateName: String,
    val toStateName: String,
    val event: TransitionEvent
)