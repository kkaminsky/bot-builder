package com.kkaminsky.statemachineapi.transition.choice_config

import com.kkaminsky.statemachineapi.transition.TransitionConfig


interface BaseChooseExternalStateConfigurer<State:Enum<State>, Event:Enum<Event>>
    : TransitionConfig<State, Event> {
}