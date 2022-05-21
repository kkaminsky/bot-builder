package com.kkaminsky.builderapi.repository

import com.kkaminsky.builderapi.entity.TransitionToEventType
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface TransitionToEventTypeRepository : PagingAndSortingRepository<TransitionToEventType, UUID> {
    fun findByTransitionIdIn(transitionsIds: List<UUID>): List<TransitionToEventType>
}