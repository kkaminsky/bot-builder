package com.kkaminsky.botbuilder.builder

import com.kkaminsky.botbuilder.template.event.BotButton
import org.springframework.stereotype.Service

@Service
class StartButtonMessageDataParser: MessageDataParser {
    override val buttonClass: Class<out BotButton>
        get() = BotButton.StateDialogBotButton::class.java

    override fun getEvent(messageData: String): String {
        return "start"
    }
}