package com.kkaminsky.botbuilder.builderapi.controller

import com.kkaminsky.botbuilder.BotBuilderApplicationTest
import com.kkaminsky.builderapi.dto.user.UserDto
import com.kkaminsky.builderapi.entity.User
import com.kkaminsky.builderapi.repository.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject

import org.springframework.boot.web.server.LocalServerPort
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTests: BotBuilderApplicationTest() {

    @LocalServerPort
    private val port = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var userRepository: UserRepository

    lateinit var userId: UUID


    @BeforeEach
    fun addUsers(){
        userId = userRepository.save(User("test","test")).id
    }

    @Test
    fun testGetUserById(){
        val res = restTemplate.getForObject("http://localhost:$port/user/get/id?id=$userId", UserDto::class.java)
        println(res)
        assert(res.id == userId)
    }

    @Test
    fun getAllUsers(){
        val res = restTemplate.getForObject<List<UserDto>>("http://localhost:$port/user/get/all")
        println(res)
    }
}