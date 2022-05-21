package com.kkaminsky.statemachineapi.state

import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.configurers.StateConfigurer

interface ActionStateConfigure<State : Enum<State>, Event : Enum<Event>>
    : com.kkaminsky.statemachineapi.state.StateConfig<State, Event> {

    val action: Action<State, Event>

    override fun initializeState(stateConfigurer: StateConfigurer<State, Event>)
            : StateConfigurer<State, Event> {
        return stateConfigurer.stateEntry(state, action, Action { context ->

            context.stateMachine.setStateMachineError(context.exception)

            throw Exception(context.exception)
        })
    }
}