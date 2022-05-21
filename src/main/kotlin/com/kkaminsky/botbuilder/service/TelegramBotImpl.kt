package com.kkaminsky.botbuilder.service


import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kkaminsky.botbuilder.service.handlers.UpdateHandler
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.Update

@Service
@EnableRabbit
class TelegramBotImpl(
    private val objectMapper: ObjectMapper,
    private val handlers: List<UpdateHandler>,
    @Value("\${telegram.username}") val username: String,
    @Value("\${telegram.token}") val token: String
) : TelegramBot, TelegramLongPollingBot(
        DefaultBotOptions()
) {

    private val logger = LoggerFactory.getLogger(TelegramBotImpl::class.java)

    override fun getBotUsername(): String {
        return username
    }

    override fun getBotToken(): String {
        return token
    }


    @RabbitListener(queues = ["out-queue-answer-inline"])
    fun onAnswerInline(message: Message) {
        val data = objectMapper.readValue<AnswerInlineQuery>(message.body)
        execute(data)
    }

    @RabbitListener(queues = ["out-queue-send-message"])
    fun onSendMessage(message: Message) {
        val data = objectMapper.readValue<SendMessage>(message.body)
        execute(data)
    }

    @RabbitListener(queues = ["out-queue-edit-text"])
    fun onEditMessageText(message: Message) {
        val data = objectMapper.readValue<EditMessageText>(message.body)
        execute(data)
    }

    override fun onUpdateReceived(p0: Update) {
        println(p0.toString())
        execute(SendMessage().apply {
            chatId = "442884052"
            text = "Someone has entered"
        })
        handlers.find { it.isSuitable(p0) }?.handle(p0)?: logger.info("Не найден обработчик для сообщения $p0")
    }
}