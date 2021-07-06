package com.kkaminsky.botbuilder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BotBuilderApplication

fun main(args: Array<String>) {
    runApplication<BotBuilderApplication>(*args)
}
