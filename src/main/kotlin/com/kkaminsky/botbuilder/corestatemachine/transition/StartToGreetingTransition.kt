package com.kkaminsky.botbuilder.corestatemachine.transition


import com.kkaminsky.botbuilder.corestatemachine.config.enums.UserBotEvent
import com.kkaminsky.botbuilder.corestatemachine.config.enums.UserBotState
import com.kkaminsky.statemachineapi.transition.external_config.EventExternalStateConfigurer
import org.springframework.stereotype.Component

@Component
data class StartToGreetingTransition(
    override val fromState: UserBotState = UserBotState.START,
    override val toState: UserBotState = UserBotState.GREETING,
    override val event: UserBotEvent = UserBotEvent.TEXT
) : EventExternalStateConfigurer<UserBotState, UserBotEvent>