package com.kkaminsky.builderapi.repository

import com.kkaminsky.builderapi.entity.Step
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface StepRepository : PagingAndSortingRepository<Step, UUID> {
    fun findByStateMachineId(stateMachineId: UUID): List<Step>
    fun findByStateMachineIdAndIsStart(stateMachineId: UUID, isStart: Boolean): Step
}