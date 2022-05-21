package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.user.UserDto
import com.kkaminsky.builderapi.dto.user.CreateUserDto
import java.util.*

interface UserService {
    fun create(model: CreateUserDto): UserDto
    fun getUser(id: UUID): UserDto
    fun getUser(telegramId: String): UserDto
    fun getAllUsers(): List<UserDto>
}