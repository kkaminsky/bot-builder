package com.kkaminsky.botbuilder.service.handlers

import com.kkaminsky.botbuilder.builder.eventparser.StringStateMachineEventParser
import com.kkaminsky.botbuilder.builder.facade.BuilderWithStepsFacadeService
import com.kkaminsky.botbuilder.config.consts.EnumEventVariables
import org.springframework.data.redis.core.RedisOperations
import org.springframework.messaging.support.GenericMessage
import org.springframework.statemachine.persist.StateMachinePersister
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.*

@Service
class CallbackViaBotQueryHandler(
    private val redisOperations: RedisOperations<String,String>,
    private val persistent: StateMachinePersister<String,String, String>,
    private val builderWithStepsFacadeService: BuilderWithStepsFacadeService,
    private val stringStateMachineEventParser: StringStateMachineEventParser
): UpdateHandler {
    override fun handle(update: Update) {
        val callback = update.callbackQuery
        val dialogId = redisOperations.opsForValue()["cq_"+callback.inlineMessageId]

        val stateMachine = builderWithStepsFacadeService.buildStateMachine(
            stateMachineId = UUID.fromString(dialogId))

        persistent.restore(stateMachine, callback.inlineMessageId)
        val message = GenericMessage(
            stringStateMachineEventParser.parseEvent(callback),
            mapOf(
                EnumEventVariables.STATE_MACHINE_ID.name to callback.inlineMessageId,
                EnumEventVariables.CALLBACK_VAI_BOT_UPDATE.name to update
            )
        )
        stateMachine.sendEvent(message)
        persistent.persist(stateMachine, callback.inlineMessageId)
    }

    override fun isSuitable(update: Update): Boolean {
        return update.callbackQuery != null && update.callbackQuery.inlineMessageId != null
    }
}