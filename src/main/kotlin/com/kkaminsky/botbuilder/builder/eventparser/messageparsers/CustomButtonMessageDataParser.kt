package com.kkaminsky.botbuilder.builder.eventparser.messageparsers

import com.kkaminsky.botbuilder.corestatemachine.action.buttons.BotButton
import com.kkaminsky.builderapi.service.EventService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomButtonMessageDataParser(
    private val eventService: EventService
): MessageDataParser {
    override val buttonClass: Class<out BotButton>
        get() = BotButton.UserCustomBotButton::class.java

    override fun getEvent(messageData: String): String {
        return eventService.getEvent(UUID.fromString(BotButton.getData(messageData))).eventType.id.toString()
    }
}