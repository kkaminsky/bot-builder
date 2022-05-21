package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.user.UserDto
import com.kkaminsky.builderapi.dto.user.CreateUserDto
import com.kkaminsky.builderapi.entity.User
import com.kkaminsky.builderapi.mapper.UserMapper
import com.kkaminsky.builderapi.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserService {

    @Transactional
    override fun create(model: CreateUserDto): UserDto {
        return userRepository.save(User(username = model.username,telegramId = model.telegramId)).let(userMapper::map)
    }

    @Transactional(readOnly = true)
    override fun getUser(id: UUID): UserDto {
        return userRepository.findById(id).map(userMapper::map).get()
    }

    @Transactional(readOnly = true)
    override fun getUser(telegramId: String): UserDto {
        return userRepository.findByTelegramId(telegramId).let(userMapper::map)
    }

    @Transactional(readOnly = true)
    override fun getAllUsers(): List<UserDto> {
        return userRepository.findAll().map(userMapper::map)
    }
}