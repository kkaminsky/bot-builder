package com.kkaminsky.botbuilder.builder.eventparser.messageparsers

import com.kkaminsky.botbuilder.config.consts.Consts
import com.kkaminsky.botbuilder.corestatemachine.action.buttons.BotButton
import org.springframework.stereotype.Service

@Service
class StartButtonMessageDataParser: MessageDataParser {
    override val buttonClass: Class<out BotButton>
        get() = BotButton.StateDialogBotButton::class.java

    override fun getEvent(messageData: String): String {
        return Consts.startDialogEventPrefix
    }
}