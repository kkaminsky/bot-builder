package com.kkaminsky.builderapi.repository

import com.kkaminsky.builderapi.entity.User
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface UserRepository: PagingAndSortingRepository<User,UUID> {
    fun findByTelegramId(telegramId: String): User
}

