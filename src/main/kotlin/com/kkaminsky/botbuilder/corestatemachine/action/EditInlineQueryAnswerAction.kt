package com.kkaminsky.botbuilder.corestatemachine.action

import com.fasterxml.jackson.databind.ObjectMapper
import com.kkaminsky.botbuilder.config.consts.EnumEventVariables
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

interface EditInlineQueryAnswerAction : Action<String, String> {
    val rabbitTemplate: RabbitTemplate
    val objectMapper: ObjectMapper

    override fun execute(context: StateContext<String, String>) {
        val messageMap = context.message.headers

        val update = messageMap.get(EnumEventVariables.CALLBACK_VAI_BOT_UPDATE.name) as Update

        val resultEdit = editText(update)

        rabbitTemplate.convertAndSend("out-queue-edit-text", objectMapper.writeValueAsString(resultEdit))
    }



    fun editText(message: Update): BotApiMethod<*>


}