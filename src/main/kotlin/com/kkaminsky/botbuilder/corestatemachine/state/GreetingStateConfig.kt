package com.kkaminsky.botbuilder.core.state

import com.kkaminsky.botbuilder.core.UserBotEvent
import com.kkaminsky.botbuilder.core.UserBotState
import com.kkaminsky.botbuilder.core.action.GreetingAction
import org.springframework.stereotype.Component

@Component
data class GreetingStateConfig(
    override val state: UserBotState = UserBotState.GREETING,
    override val action: GreetingAction
) : com.kkaminsky.statemachineapi.state.ActionStateConfigure<UserBotState, UserBotEvent>