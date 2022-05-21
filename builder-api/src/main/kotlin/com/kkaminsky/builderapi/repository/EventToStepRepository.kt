package com.kkaminsky.builderapi.repository

import com.kkaminsky.builderapi.entity.EventToStep
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface EventToStepRepository : PagingAndSortingRepository<EventToStep, UUID> {
    fun findByStepId(stepId: UUID): List<EventToStep>
    fun findByStepIdIn(steps: List<UUID>): List<EventToStep>
}