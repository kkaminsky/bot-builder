package com.kkaminsky.statemachineapi.state

import org.springframework.statemachine.config.configurers.StateConfigurer


interface StateConfig<State: Enum<State>, Event: Enum<Event>> {

    val state: State

    fun initializeState(stateConfigurer: StateConfigurer<State, Event>) : StateConfigurer<State, Event>
}