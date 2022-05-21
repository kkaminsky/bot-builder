package com.kkaminsky.botbuilder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.kkaminsky.builderapi","com.kkaminsky.botbuilder"])
@EnableJpaRepositories("com.kkaminsky.builderapi")
@EntityScan("com.kkaminsky.builderapi")
class BotBuilderApplication

fun main(args: Array<String>) {
    runApplication<BotBuilderApplication>(*args)
}
