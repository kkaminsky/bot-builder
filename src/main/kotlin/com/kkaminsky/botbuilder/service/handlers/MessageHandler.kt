package com.kkaminsky.botbuilder.service.handlers

import com.kkaminsky.botbuilder.consts.EnumEventVariables
import com.kkaminsky.botbuilder.core.UserBotEvent
import com.kkaminsky.botbuilder.core.UserBotState
import org.springframework.messaging.support.GenericMessage
import org.springframework.statemachine.config.StateMachineFactory
import org.springframework.statemachine.persist.StateMachinePersister
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class DefaultHandler(
    private val stateMachineFactory: StateMachineFactory<UserBotState, UserBotEvent>,
    private val persistent: StateMachinePersister<UserBotState, UserBotEvent, String>
) : UpdateHandler {
    override fun isSuitable(update: Update): Boolean {
        return update.message != null
    }

    override fun handle(update: Update) {
        val stateMachineId = if (update.callbackQuery != null) {
            update.callbackQuery.message.chatId
        } else {
            update.message.chatId
        }.toString()
        val stateMachine = stateMachineFactory.getStateMachine()
        persistent.restore(stateMachine, stateMachineId)
        val message = GenericMessage(
            UserBotEvent.TEXT,
            mapOf(
                EnumEventVariables.STATE_MACHINE_ID.name to stateMachineId,
                EnumEventVariables.MESSAGE.name to update
            )
        )
        stateMachine.sendEvent(message)
        persistent.persist(stateMachine, stateMachineId)
    }
}