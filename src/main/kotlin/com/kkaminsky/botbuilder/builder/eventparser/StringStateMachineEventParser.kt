package com.kkaminsky.botbuilder.builder.eventparser

import org.telegram.telegrambots.meta.api.objects.CallbackQuery

interface StringStateMachineEventParser {
    fun parseEvent(update: CallbackQuery): String
}