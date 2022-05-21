package com.kkaminsky.builderapi.repository

import com.kkaminsky.builderapi.entity.Event
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface EventRepository : PagingAndSortingRepository<Event, UUID> {
    fun findByEventTypeId(eventTypeId: UUID): List<Event>
    fun findByEventTypeIdIn(eventTypes: List<UUID>): List<Event>
}