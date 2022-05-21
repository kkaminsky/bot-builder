package com.kkaminsky.botbuilder.service


import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kkaminsky.botbuilder.consts.EnumEventVariables
import com.kkaminsky.botbuilder.core.UserBotEvent
import com.kkaminsky.botbuilder.core.UserBotState
import com.kkaminsky.botbuilder.core.action.NewMessageDto
import com.kkaminsky.botbuilder.template.event.BotButton
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.support.GenericMessage
import org.springframework.statemachine.config.StateMachineFactory
import org.springframework.statemachine.persist.StateMachinePersister
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.methods.webapp.AnswerWebAppQuery
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputMessageContent
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import java.io.Serializable
import java.lang.reflect.Method
import java.util.*

@Service
@EnableRabbit
class TelegramBotImpl(
    private val stateMachineFactory: StateMachineFactory<UserBotState,UserBotEvent>,
    private val persistent: StateMachinePersister<UserBotState, UserBotEvent, String>,
    private val objectMapper: ObjectMapper
) : TelegramBot, TelegramLongPollingBot(
        DefaultBotOptions()
) {

    private val logger = LoggerFactory.getLogger(TelegramBotImpl::class.java)

    override fun getBotUsername(): String {
        return "kk_tezt_bot"
    }

    override fun getBotToken(): String {
        return "1104890578:AAHZnxNxyQ302E3rbs3FjV6QmLvL9FiWVdQ"
    }


    override fun <T : Serializable?, Method : BotApiMethod<T>?> executeMethod(method: Method?): T {
        try {
            return execute(method)
        } catch (e: TelegramApiRequestException) {
            throw Exception("Ошибка при обращении к апи телеграмма: " + e.apiResponse + ". " + e.localizedMessage)
        }
    }


    @RabbitListener(queues = ["spring-boot"])
    fun onMessage(message: Message?) {
        val data = objectMapper.readValue<NewMessageDto>(message!!.body)
        executeMethod(SendMessage().apply {
            chatId = data.chatId
            text = data.text
            parseMode = ParseMode.HTML
            replyMarkup = createKeyboardForced(listOf())
        })

    }

    fun createKeyboard(listButtons: List<List<BotButton>>): InlineKeyboardMarkup {
        val markup = InlineKeyboardMarkup()
        markup.apply {
            keyboard = listButtons.map {listBotButton ->
                listBotButton.map { botButton ->
                    val button = InlineKeyboardButton()
                    button.apply {
                        botButton.link?.also{lnk->webApp = WebAppInfo(lnk) }
                        text = botButton.displayName
                    }
                }
            }
        }
        return markup
    }

    fun createKeyboardForced(listButtons: List<List<BotButton>>): ReplyKeyboardMarkup {
        val markup = ReplyKeyboardMarkup()
        markup.apply {
            val keyboardRow = KeyboardRow(listOf(KeyboardButton("test").apply { webApp = WebAppInfo(
                "https://www.mr.kkaminsky.fvds.ru"
            ) }))
            keyboard = listOf(keyboardRow)
        }
        return markup
    }

    override fun onUpdateReceived(p0: Update?) {
        println(p0.toString())
        if(p0!!.message.webAppData!=null){
            executeMethod(DeleteMessage().apply {
                chatId = p0.message.chatId.toString()
                messageId = p0.message.messageId
            })
            println(p0!!.message.webAppData.data)
            executeMethod(AnswerWebAppQuery().apply {
                    webAppQueryId = p0!!.message.webAppData.data
                    queryResult = InlineQueryResultArticle().apply {
                        id = UUID.randomUUID().toString()
                        title = "test"
                        inputMessageContent = InputTextMessageContent().apply {
                            id = UUID.randomUUID().toString()
                            messageText = "test text"
                        }
                }
            })
        }
        val stateMachineId = if(p0!!.callbackQuery!=null){
            p0.callbackQuery.message.chatId
        } else {
            p0.message.chatId
        }.toString()

        println(stateMachineId)

        val stateMachine = stateMachineFactory.getStateMachine()
        persistent.restore(stateMachine,stateMachineId)
        val message = GenericMessage(UserBotEvent.START,
            mapOf(
                EnumEventVariables.STATE_MACHINE_ID.name to stateMachineId,
                EnumEventVariables.MESSAGE.name to p0
            )
        )
        stateMachine.sendEvent(message)
        persistent.persist(stateMachine,stateMachineId)

        println("save")
    }
}