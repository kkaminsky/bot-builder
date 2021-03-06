package com.kkaminsky.botbuilder.corestatemachine.action

import com.fasterxml.jackson.databind.ObjectMapper
import com.kkaminsky.botbuilder.corestatemachine.action.buttons.BotButton
import com.kkaminsky.builderapi.dto.user.CreateUserDto
import com.kkaminsky.builderapi.service.facade.UserWithStatemachineFacadeService
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo

@Component
class GreetingAction(
    override val rabbitTemplate: RabbitTemplate,
    override val objectMapper: ObjectMapper,
    private val userWithStatemachineFacadeService: UserWithStatemachineFacadeService
) : UserMessageAction<Message> {

    override fun executeWithMessage(message: Update): BotApiMethod<Message> {
        userWithStatemachineFacadeService.createNewUserAndStateMachine(CreateUserDto(
            telegramId =  message.message.from.id.toString(),
            username = message.message.from.userName
        ))
        return SendMessage().apply {
            chatId = message.message.chatId.toString()
            text = "Hello!"
            replyMarkup = createKeyboard(listOf(listOf(
                BotButton.WebBotButton(displayName = "App",link = "https://kks.fvds.ru/"))))
        }
    }


    fun createKeyboard(listButtons: List<List<BotButton>>): InlineKeyboardMarkup {
        val markup = InlineKeyboardMarkup()
        markup.apply {
            keyboard = listButtons.map {listBotButton ->
                listBotButton.map { botButton ->
                    val button = InlineKeyboardButton()
                    button.apply {
                        botButton.link?.also{lnk->webApp = WebAppInfo(lnk)}
                        text = botButton.displayName
                    }
                }
            }
        }
        return markup
    }

}