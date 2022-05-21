package com.kkaminsky.builderapi.repository

import com.kkaminsky.builderapi.entity.EventType
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface EventTypeRepository : PagingAndSortingRepository<EventType, UUID> {
    fun findByStateMachineId(stateMachineId: UUID): List<EventType>
}