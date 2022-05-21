package com.kkaminsky.botbuilder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.kkaminsky.statemachineapi", "com.kkaminsky.botbuilder"])
class BotBuilderApplication

fun main(args: Array<String>) {
    runApplication<BotBuilderApplication>(*args)
}
