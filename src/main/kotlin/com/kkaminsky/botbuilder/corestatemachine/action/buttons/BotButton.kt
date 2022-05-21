package com.kkaminsky.botbuilder.template.event


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kkaminsky.botbuilder.consts.Consts
import kotlin.reflect.full.findAnnotation

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
sealed class BotButton() {

    abstract val displayName: String
    abstract val messageData: String
    abstract val link: String?

    val messageButtonData: String by lazy{mainPrefix + prefix + separator + messageData}
    val prefix: String = this::class.findAnnotation<BotButtonAnnotation>()?.prefix?:""


    companion object {
        const val separator: String = "_"
        const val mainPrefix = "/"

        private val prefixButtons = BotButton::class.sealedSubclasses.associate { kClass ->
            val prefix = kClass.findAnnotation<BotButtonAnnotation>()?.prefix
                    ?: throw Exception("${kClass.simpleName} не содержит корректную аннотацию! ")
            prefix to kClass.java
        }

        fun valueOf(value: String): Class<out BotButton> {
            val classificator = value.replace(mainPrefix, "").split(separator)[0]
            return prefixButtons[classificator]
                    ?: throw Exception("Не удалось определить класс для $value, т.к. не нашлось ниодного подходящего класса")
        }

        fun isItThisButton(messageData: String, clazz: Class<out BotButton>): Boolean {
            return valueOf(messageData) == clazz
        }

        fun getData(messageData: String): String {
            return messageData.split(separator)[1]
        }
    }

    @BotButtonAnnotation(prefix = "web")
    data class WebBotButton(override val displayName: String,
                            override val messageData: String = "",
                            override val link: String): BotButton()


    @BotButtonAnnotation(prefix = Consts.startDialogEventPrefix)
    data class StateDialogBotButton(override val displayName: String,
                                   override val messageData: String,
                                   override val link: String? = null): BotButton()


    @BotButtonAnnotation(prefix = "userCustom")
    data class UserCustomBotButton(override val displayName: String,
                                   override val messageData: String,
                                   override val link: String? = null) : BotButton()
}

