package com.kkaminsky.statemachineapi.transition.choice_config

import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.ChoiceTransitionConfigurer

interface ChooseExternalStateConfigurer<State:Enum<State>,Event: Enum<Event>> :
    ChooseExternalWithoutAction<State, Event> {

    val firstAction: Action<State, Event>
    val firstErrorAction: Action<State,Event>

    override fun initializeConfig(baseConfigurer: StateMachineTransitionConfigurer<State, Event>)
    : ChoiceTransitionConfigurer<State, Event> {
        return baseConfigurer
                .withChoice()
                .source(fromState)
                .first(firstState,firstGuard,firstAction,firstErrorAction)
                .last(secondState)

    }

}