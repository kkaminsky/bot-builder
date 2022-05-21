package com.kkaminsky.botbuilder.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@EnableRabbit
@Configuration
class RabbitMqConfig (private val connectionFactory: org.springframework.amqp.rabbit.connection.ConnectionFactory) {



    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
            .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
            .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }

    @Bean
    fun pushBoardEvent(): Queue {
        return Queue("out-queue-answer-inline",false)
    }

    @Bean
    fun pushBoardEvent2(): Queue {
        return Queue("out-queue-send-message",false)
    }

    @Bean
    fun pushBoardEvent3(): Queue {
        return Queue("out-queue-edit-message-reply",false)
    }

    @Bean
    fun pushBoardEvent4(): Queue {
        return Queue("out-queue-edit-text",false)
    }
}
