package com.kkaminsky.botbuilder.service.handlers

import org.springframework.data.redis.core.RedisOperations
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class ChosenInlineQueryHandler(
    private val redisOperations: RedisOperations<String,String>
): UpdateHandler {

    override fun handle(update: Update) {
        val cQ = update.chosenInlineQuery
        redisOperations.opsForValue()["cq_" + cQ.inlineMessageId] = cQ.resultId
    }

    override fun isSuitable(update: Update): Boolean {
        return update.chosenInlineQuery != null
    }
}