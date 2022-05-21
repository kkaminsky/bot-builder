package com.kkaminsky.botbuilder.builderapi.service

import com.kkaminsky.botbuilder.BotBuilderApplicationTest
import com.kkaminsky.builderapi.entity.User
import com.kkaminsky.builderapi.repository.UserRepository
import com.kkaminsky.builderapi.service.UserService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean

internal class UserServiceImplTest: BotBuilderApplicationTest() {

    @MockBean
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userService: UserService

    @Test
    fun create() {
        Mockito.`when`(userRepository.findAll()).thenReturn(listOf(User("test_mock","test_mock")))
        val res = userService.getAllUsers()
        println(res)
    }
}