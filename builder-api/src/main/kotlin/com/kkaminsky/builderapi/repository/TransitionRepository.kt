package com.kkaminsky.builderapi.repository

import com.kkaminsky.builderapi.entity.Transition
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface TransitionRepository: PagingAndSortingRepository<Transition, UUID> {
    fun findByStateMachineId(stateMachineId: UUID): List<Transition>
}