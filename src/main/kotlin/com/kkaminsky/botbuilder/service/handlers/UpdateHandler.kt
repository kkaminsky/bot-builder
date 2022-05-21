package com.kkaminsky.botbuilder.service.handlers

import com.kkaminsky.botbuilder.corestatemachine.action.buttons.BotButton
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

interface UpdateHandler {
    fun handle(update: Update)
    fun isSuitable(update: Update): Boolean

    fun createKeyboard(listButtons: List<List<BotButton>>): InlineKeyboardMarkup {
        val markup = InlineKeyboardMarkup()
        markup.apply {
            keyboard = listButtons.map {listBotButton ->
                listBotButton.map { botButton ->
                    val button = InlineKeyboardButton()
                    button.apply {
                        botButton.link?.also{lnk->url = lnk}
                        callbackData = botButton.messageButtonData
                        text = botButton.displayName
                    }
                }
            }
        }
        return markup
    }
}


