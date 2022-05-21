package com.kkaminsky.botbuilder.core.transition


import com.kkaminsky.botbuilder.core.UserBotEvent
import com.kkaminsky.botbuilder.core.UserBotState
import com.kkaminsky.statemachineapi.transition.external_config.EventExternalStateConfigurer
import org.springframework.stereotype.Component

@Component
data class StartToGreetingTransition(
    override val fromState: UserBotState = UserBotState.START,
    override val toState: UserBotState = UserBotState.GREETING,
    override val event: UserBotEvent = UserBotEvent.TEXT
) : EventExternalStateConfigurer<UserBotState, UserBotEvent>