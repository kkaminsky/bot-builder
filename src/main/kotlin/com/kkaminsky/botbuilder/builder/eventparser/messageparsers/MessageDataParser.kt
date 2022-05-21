package com.kkaminsky.botbuilder.builder

import com.kkaminsky.botbuilder.template.event.BotButton

interface MessageDataParser{
    val buttonClass: Class<out BotButton>
    fun isSuitable(messageData: String): Boolean{
        return BotButton.isItThisButton(messageData, buttonClass)
    }
    fun getEvent(messageData: String): String
}