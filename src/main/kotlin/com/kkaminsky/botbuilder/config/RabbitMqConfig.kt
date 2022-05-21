package com.kkaminsky.botbuilder.config

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.HashMap
import java.util.concurrent.CountDownLatch





/*
@Configuration
@EnableRabbit
class RabbitMqConfig {

    val topicExchangeName = "spring-boot-exchange"

    val queueName = "spring-boot"

    @Bean
    fun queue(): Queue? {
        return Queue(queueName, false)
    }

    @Bean
    fun exchange(): TopicExchange? {
        return TopicExchange(topicExchangeName)
    }

    @Bean
    fun binding(queue: Queue?, exchange: TopicExchange?): Binding? {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#")
    }

    @Bean
    fun container(
        connectionFactory: ConnectionFactory,
        messageListener: MessageListener
    ): SimpleMessageListenerContainer? {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(queueName)
        container.setMessageListener(messageListener)
        return container
    }

    */
/*@Bean
    fun listenerAdapter(receiver: Receiver): MessageListenerAdapter? {

        return MessageListenerAdapter(receiver, "receiveMessage")
    }*//*

}
*/



/*
@Component
@EnableRabbit
class Receiver  {



    fun receiveMessage(message: String) {
        println("Received <$message>")

    }

    @RabbitListener(queues = ["spring-boot"])
    fun onMessage(message: Message?) {
        println("Received 2 <$message>")
    }
}
*/





@EnableRabbit
@Configuration
class RabbitMqConfigd (private val connectionFactory: org.springframework.amqp.rabbit.connection.ConnectionFactory) {



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
        return Queue("spring-boot",false)
    }
}
