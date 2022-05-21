package com.kkaminsky.botbuilder.corestatemachine.state

import com.kkaminsky.botbuilder.corestatemachine.config.enums.UserBotEvent
import com.kkaminsky.botbuilder.corestatemachine.config.enums.UserBotState
import com.kkaminsky.botbuilder.corestatemachine.action.GreetingAction
import org.springframework.stereotype.Component

@Component
data class GreetingStateConfig(
    override val state: UserBotState = UserBotState.GREETING,
    override val action: GreetingAction
) : com.kkaminsky.statemachineapi.state.ActionStateConfigure<UserBotState, UserBotEvent>