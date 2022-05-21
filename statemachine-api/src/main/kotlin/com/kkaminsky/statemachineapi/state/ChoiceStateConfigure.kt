package com.kkaminsky.statemachineapi.state

import org.springframework.statemachine.config.configurers.StateConfigurer

interface ChoiceStateConfigure <State: Enum<State>, Event: Enum<Event>>
    : com.kkaminsky.statemachineapi.state.StateConfig<State, Event> {

    override fun initializeState(stateConfigurer: StateConfigurer<State, Event>): StateConfigurer<State, Event> {
        return stateConfigurer.choice(state)
    }
}