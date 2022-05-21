package com.kkaminsky.botbuilder.builder.eventparser.messageparsers

import com.kkaminsky.botbuilder.corestatemachine.action.buttons.BotButton

interface MessageDataParser{
    val buttonClass: Class<out BotButton>
    fun isSuitable(messageData: String): Boolean{
        return BotButton.isItThisButton(messageData, buttonClass)
    }
    fun getEvent(messageData: String): String
}