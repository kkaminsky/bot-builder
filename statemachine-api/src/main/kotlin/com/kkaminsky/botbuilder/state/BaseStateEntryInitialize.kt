package com.kkaminsky.botbuilder.state

import org.springframework.statemachine.config.configurers.StateConfigurer


interface BaseStateEntryInitialize<State: Enum<State>, Event: Enum<Event>> {

    val state: State

    fun initializeStateConfigurer (stateConfigurer: StateConfigurer<State, Event>) : StateConfigurer<State, Event>
}