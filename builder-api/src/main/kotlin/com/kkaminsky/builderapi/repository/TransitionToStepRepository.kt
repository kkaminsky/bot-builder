package com.kkaminsky.builderapi.repository

import com.kkaminsky.builderapi.dto.DirectionType
import com.kkaminsky.builderapi.entity.TransitionToStep
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface TransitionToStepRepository : PagingAndSortingRepository<TransitionToStep, UUID> {
    fun findByTransitionIdIn(transitionIds: List<UUID>): List<TransitionToStep>
    fun findByStepId(stepId: UUID): List<TransitionToStep>
    fun findByTransitionIdAndDirectionType(transitionId: UUID, directionType: DirectionType): TransitionToStep
}