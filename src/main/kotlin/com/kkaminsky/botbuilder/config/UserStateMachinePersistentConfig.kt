package com.kkaminsky.botbuilder.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kkaminsky.botbuilder.core.UserBotEvent
import com.kkaminsky.botbuilder.core.UserBotState
import liquibase.pro.packaged.S
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.statemachine.StateMachineContext
import org.springframework.statemachine.StateMachinePersist
import org.springframework.statemachine.persist.DefaultStateMachinePersister
import org.springframework.statemachine.persist.StateMachinePersister
import org.springframework.statemachine.support.DefaultExtendedState
import org.springframework.statemachine.support.DefaultStateMachineContext
import java.util.*


@Configuration
class UserStateMachinePersistentConfig {


    @Bean
    fun createDefaultTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, ByteArray> {
        val template = RedisTemplate<String, ByteArray>()
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.setConnectionFactory(connectionFactory)
        template.afterPropertiesSet()
        return template
    }

    @Bean
    fun getPersistentEnum(redisOperations: RedisOperations<String, String>,
                      objectMapper: ObjectMapper): StateMachinePersister<UserBotState, UserBotEvent,String?>?{


        return DefaultStateMachinePersister(SessionStateMachinePersist(redisOperations, objectMapper))
    }

    @Bean
    fun getPersistentString(redisOperations: RedisOperations<String, String>,
                      objectMapper: ObjectMapper): StateMachinePersister<String, String,String?>? {

        return DefaultStateMachinePersister(SessionStateMachinePersistString(redisOperations,objectMapper))
    }


}



class SessionStateMachinePersistString(private val redisOperations: RedisOperations<String, String>,
                                 private val objectMapper: ObjectMapper) : StateMachinePersist<String, String, String> {



    override fun read(contextObj: String): StateMachineContext<String, String>? {
        val stateMachineContext = getContext(contextObj)
        return DefaultStateMachineContext(stateMachineContext?.childs ?: mutableListOf(), stateMachineContext?.state?:"", stateMachineContext?.event?:"",
            stateMachineContext?.eventHeaders, DefaultExtendedState(stateMachineContext?.extendedState?.variables ?: mutableMapOf()), stateMachineContext?.historyStates)
    }

    override fun write(context: StateMachineContext<String, String>, contextObj: String) {
        val serializableExtendedState = SerializableExtendedState(context.extendedState.variables.toMap())
        val serializableStateMachineContext = SerializableStateMachineContextString(null, context.childs, context.state, context.historyStates, context.event, context.eventHeaders, serializableExtendedState)
        save(serializableStateMachineContext,contextObj)
    }


    fun getContext(id: String): SerializableStateMachineContextString? {
        return deserialize(redisOperations.opsForValue()[id])
    }


    private fun deserialize(data: String?): SerializableStateMachineContextString? {
        if (data == null || data.length == 0) {
            return null
        }
        return objectMapper.readValue<SerializableStateMachineContextString>(data)
    }

    fun save(context: SerializableStateMachineContextString?, id: String) {
        redisOperations.opsForValue()[id] = serialize(context)
    }

    private fun serialize(context: SerializableStateMachineContextString?): String {
        return objectMapper.writeValueAsString(context)
    }

}


class SessionStateMachinePersist(private val redisOperations: RedisOperations<String, String>,
                                 private val objectMapper: ObjectMapper) : StateMachinePersist<UserBotState, UserBotEvent, String> {



    override fun read(contextObj: String): StateMachineContext<UserBotState,UserBotEvent>? {
        val stateMachineContext = getContext(contextObj)
        return DefaultStateMachineContext(stateMachineContext?.childs ?: mutableListOf(), stateMachineContext?.state, stateMachineContext?.event,
            stateMachineContext?.eventHeaders, DefaultExtendedState(stateMachineContext?.extendedState?.variables ?: mutableMapOf()), stateMachineContext?.historyStates)
    }

    override fun write(context: StateMachineContext<UserBotState,UserBotEvent>, contextObj: String) {
        val serializableExtendedState = SerializableExtendedState(context.extendedState.variables.toMap())
        val serializableStateMachineContext = SerializableStateMachineContext(null, context.childs, context.state, context.historyStates, context.event, context.eventHeaders, serializableExtendedState)
        save(serializableStateMachineContext,contextObj)
    }


    fun getContext(id: String): SerializableStateMachineContext<UserBotState,UserBotEvent>? {
        return deserialize(redisOperations.opsForValue()[id])
    }


    private fun deserialize(data: String?): SerializableStateMachineContext<UserBotState,UserBotEvent>? {
        if (data == null || data.length == 0) {
            return null
        }
        return objectMapper.readValue<SerializableStateMachineContext<UserBotState,UserBotEvent>>(data)
    }

    fun save(context: SerializableStateMachineContext<UserBotState,UserBotEvent>?, id: String) {
        redisOperations.opsForValue()[id] = serialize(context)
    }

    private fun serialize(context: SerializableStateMachineContext<UserBotState,UserBotEvent>?): String {
        return objectMapper.writeValueAsString(context)
    }

}


class SerializableStateMachineContext<S, E>(var id: String?, var childs: List<StateMachineContext<S, E>>?, var state: S?,
                                            var historyStates: Map<S, S>?, var event: E?, var eventHeaders: Map<String, Any>?,
                                            var extendedState: SerializableExtendedState?){

    constructor() : this(null, null, null, null, null, null, null)

}

class SerializableExtendedState(var variables: Map<Any, Any>){

    constructor() : this(mutableMapOf())

}


class SerializableStateMachineContextString(var id: String?, var childs: List<StateMachineContext<String, String>>?, var state: String?,
                                            var historyStates: Map<String, String>?, var event: String?, var eventHeaders: Map<String, Any>?,
                                            var extendedState: SerializableExtendedState?){

    constructor() : this(null, null, null, null, null, null, null)

}