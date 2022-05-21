package com.kkaminsky.botbuilder.corestatemachine.action

import com.fasterxml.jackson.databind.ObjectMapper
import com.kkaminsky.botbuilder.corestatemachine.action.buttons.BotButton
import com.kkaminsky.builderapi.dto.step.StepWithEventsDto
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class CustomAction(private val step: StepWithEventsDto,
                   override val objectMapper: ObjectMapper,
                   override val rabbitTemplate: RabbitTemplate
): EditInlineQueryAnswerAction {


    override fun editText(message: Update): BotApiMethod<*> {
        val editText = EditMessageText()
        editText.inlineMessageId = message.callbackQuery.inlineMessageId
        editText.text = step.text
        editText.replyMarkup = createKeyboard(step.events.map {
            listOf(
                BotButton.UserCustomBotButton(
                displayName = it.text,
                messageData = it.id.toString()
            ))
        })
        return editText
    }

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