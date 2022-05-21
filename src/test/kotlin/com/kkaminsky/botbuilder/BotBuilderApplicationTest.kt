package com.kkaminsky.botbuilder

import com.kkaminsky.builderapi.entity.User
import com.kkaminsky.builderapi.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = [BotBuilderApplicationTest.Companion.DockerPostgreDataSourceInitializer::class])
@Testcontainers
class BotBuilderApplicationTest {

    companion object {
        @Container
        val postgresqlContainer = PostgreSQLContainer<Nothing>("postgres:13").apply {
            withDatabaseName("bot-builder")
            withUsername("test")
            withPassword("test")
        }


        class DockerPostgreDataSourceInitializer :
            ApplicationContextInitializer<ConfigurableApplicationContext> {
            override fun initialize(applicationContext: ConfigurableApplicationContext) {
                val jdbcUrl = postgresqlContainer.jdbcUrl
                TestPropertyValues.of("spring.datasource.url=$jdbcUrl",
                    "spring.datasource.username=" + postgresqlContainer.username,
                    "spring.datasource.password=" + postgresqlContainer.password).applyTo(applicationContext)
            }
        }
    }
}