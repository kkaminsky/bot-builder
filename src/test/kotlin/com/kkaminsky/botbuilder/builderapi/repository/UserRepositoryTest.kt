package com.kkaminsky.botbuilder.builderapi.repository

import com.kkaminsky.botbuilder.BotBuilderApplicationTest
import com.kkaminsky.builderapi.entity.User
import com.kkaminsky.builderapi.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class UserRepositoryTest: BotBuilderApplicationTest() {

    @Test
    fun saveTest(){
        val a = userRepository.save(User("test_rep","test_rep"))
        val b  = userRepository.findById(a.id)
        assert(a.id == b.get().id)
    }

    @Autowired
    lateinit var userRepository: UserRepository
}