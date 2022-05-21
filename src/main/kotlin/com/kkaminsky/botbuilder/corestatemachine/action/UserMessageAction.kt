package com.kkaminsky.botbuilder.corestatemachine.action

import com.fasterxml.jackson.databind.ObjectMapper
import com.kkaminsky.botbuilder.config.consts.EnumEventVariables
import com.kkaminsky.botbuilder.corestatemachine.config.enums.UserBotEvent
import com.kkaminsky.botbuilder.corestatemachine.config.enums.UserBotState
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update
import java.io.Serializable

interface UserMessageAction<T: Serializable>: Action<UserBotState, UserBotEvent> {

    val rabbitTemplate: RabbitTemplate
    val objectMapper: ObjectMapper

    override fun execute(context: StateContext<UserBotState, UserBotEvent>) {
        val messageMap = context.message.headers

        val result = executeWithMessage(messageMap.get(EnumEventVariables.MESSAGE.name) as Update)

        rabbitTemplate.convertAndSend("out-queue-send-message", objectMapper.writeValueAsString(result))
    }

    fun  executeWithMessage(message: Update): BotApiMethod<T>
}