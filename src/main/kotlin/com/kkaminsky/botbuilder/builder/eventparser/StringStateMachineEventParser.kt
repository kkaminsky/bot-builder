package com.kkaminsky.botbuilder.builder

import org.telegram.telegrambots.meta.api.objects.CallbackQuery

interface StringStateMachineEventParser {
    fun parseEvent(update: CallbackQuery): String
}