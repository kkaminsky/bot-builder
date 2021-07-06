package com.kkaminsky.botbuilder.persister


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.statemachine.StateMachinePersist
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository
import org.springframework.statemachine.data.redis.RedisStateMachinePersister
import org.springframework.statemachine.persist.RepositoryStateMachinePersist
import org.springframework.statemachine.persist.StateMachinePersister

@Configuration
class StringStateMachinePersister {

    @Autowired
    lateinit var jedisConnectionFactory: JedisConnectionFactory

    @Bean
    fun persister(): StateMachinePersister<String, String, String?>? {
        return RedisStateMachinePersister(stateMachinePersist())
    }

    private fun stateMachinePersist(): StateMachinePersist<String, String, String?>? {
        val repository = RedisStateMachineContextRepository<String, String>(jedisConnectionFactory)
        return RepositoryStateMachinePersist(repository)
    }
}