package com.kkaminsky.builderapi.repository

import com.kkaminsky.builderapi.entity.StateMachine
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface StateMachineRepository : PagingAndSortingRepository<StateMachine, UUID> {
    fun findByUserId(userId: UUID): List<StateMachine>
}