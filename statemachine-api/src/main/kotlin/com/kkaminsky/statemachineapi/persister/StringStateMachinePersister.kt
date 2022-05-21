package com.kkaminsky.statemachineapi.persister


/*
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
}*/
