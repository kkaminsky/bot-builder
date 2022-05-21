package com.kkaminsky.statemachineapi.transition.choice_config

import com.kkaminsky.statemachineapi.transition.TransitionConfig


interface BaseChooseExternalStateConfigurer<State:Enum<State>, Event:Enum<Event>>
    : com.kkaminsky.statemachineapi.transition.TransitionConfig<State, Event> {
}