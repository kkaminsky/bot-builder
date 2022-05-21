package com.kkaminsky.botbuilder.service.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import com.kkaminsky.botbuilder.corestatemachine.action.buttons.BotButton
import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import com.kkaminsky.builderapi.service.facade.UserWithStatemachineFacadeService
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle

@Service
class InlineQueryHandler(
    private val userWithStatemachineFacadeService: UserWithStatemachineFacadeService,
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper
) : UpdateHandler {

    override fun handle(update: Update) {
        val inlineQuery = update.inlineQuery
        val stateMachines = userWithStatemachineFacadeService.getStateMachines(inlineQuery.from.id.toString())
        val answerInlineQuery = AnswerInlineQuery()
        answerInlineQuery.inlineQueryId = inlineQuery.id
        answerInlineQuery.isPersonal = true
        answerInlineQuery.cacheTime = 5
        answerInlineQuery.switchPmText = "Create new dialog"
        answerInlineQuery.switchPmParameter = "newdialog"
        answerInlineQuery.results = generateInlineButtons(stateMachines)
        rabbitTemplate.convertAndSend("out-queue-answer-inline", objectMapper.writeValueAsString(answerInlineQuery))

    }


    override fun isSuitable(update: Update): Boolean {
        return update.inlineQuery != null
    }

    private fun generateInlineButtons(items: List<StateMachineDto>): List<InlineQueryResultArticle> {
        val result = mutableListOf<InlineQueryResultArticle>()
        items.mapTo(result) { card ->
            InlineQueryResultArticle().apply {
                id = card.id.toString()
                inputMessageContent = InputTextMessageContent().apply {
                    messageText = card.name
                    description = "Start " + card.name
                    title = card.name
                    replyMarkup = createKeyboard(listOf(listOf(
                        BotButton.StateDialogBotButton(
                            displayName = "Start " + card.name,
                            messageData = card.id.toString()
                        )
                    )))
                }
            }
        }
        return result
    }
}



