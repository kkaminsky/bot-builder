package com.kkaminsky.botbuilder.builder

import com.kkaminsky.botbuilder.template.event.BotButton
import com.kkaminsky.builderapi.service.EventService
import com.kkaminsky.builderapi.service.EventTypeService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import java.util.*

@Service
class StringStateMachineEventParserImpl(

) : StringStateMachineEventParser {
    override fun parseEvent(update: CallbackQuery): String {
        val messageData = update.data
        if (BotButton.isItThisButton(messageData,BotButton.StateDialogBotButton::class.java)){
            return "start"
        } else
    }
}


interface MessageDataParser{
    val buttonClass: Class<out BotButton>
    fun isSuitable(messageData: String): Boolean{
        return BotButton.isItThisButton(messageData,buttonClass)
    }
    fun getEvent(messageData: String): String
}

@Service
class StartButtonMessageDataParser: MessageDataParser {
    override val buttonClass: Class<out BotButton>
        get() = BotButton.StateDialogBotButton::class.java

    override fun getEvent(messageData: String): String {
        return "start"
    }
}

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