package com.kkaminsky.statemachineapi.state

import org.springframework.statemachine.config.configurers.StateConfigurer

interface ChoiceStateConfigure <State: Enum<State>, Event: Enum<Event>>
    : StateConfig<State, Event> {

    override fun initializeState(stateConfigurer: StateConfigurer<State, Event>): StateConfigurer<State, Event> {
        return stateConfigurer.choice(state)
    }
}