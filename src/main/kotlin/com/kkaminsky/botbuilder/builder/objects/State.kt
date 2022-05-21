package com.kkaminsky.botbuilder.builder.objects

import org.springframework.statemachine.action.Action

data class State(
    val name: String,
    val action: Action<String, String>
)