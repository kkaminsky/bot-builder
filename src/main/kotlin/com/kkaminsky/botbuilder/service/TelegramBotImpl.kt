package com.kkaminsky.botbuilder.service

import com.kkaminsky.botbuilder.builder.SimpleService
import com.kkaminsky.botbuilder.builder.StringStateMachineBuilder
import org.slf4j.LoggerFactory
import org.springframework.statemachine.persist.StateMachinePersister
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import java.io.Serializable

@Service
class TelegramBotImpl(
    val simpleService: SimpleService,
    val stringStateMachineBuilder: StringStateMachineBuilder
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

    override fun onUpdateReceived(p0: Update?) {
        val sm = stringStateMachineBuilder.build()
        println(sm)
        sm.sendEvent("E1")
        println("?")
    }
}