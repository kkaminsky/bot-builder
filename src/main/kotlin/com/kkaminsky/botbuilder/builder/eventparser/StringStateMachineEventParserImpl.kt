package com.kkaminsky.botbuilder.builder.eventparser

import com.kkaminsky.botbuilder.builder.eventparser.messageparsers.MessageDataParser
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import java.util.*

@Service
class StringStateMachineEventParserImpl(
    private val messageParsers: List<MessageDataParser>
) : StringStateMachineEventParser {
    override fun parseEvent(update: CallbackQuery): String {
        val messageData = update.data
        return messageParsers.find { it.isSuitable(messageData) }?.getEvent(messageData)?: "not_found_event_${messageData}"
    }
}