package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.statemachine.CreateStateMachineDto
import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import java.util.*

interface StateMachineService {
    fun getAllStateMachines(userId: UUID): List<StateMachineDto>
    fun getStateMachineRandom(userId: UUID): StateMachineDto
    fun getStateMachine(stateMachineId: UUID) : StateMachineDto
    fun createStateMachine(model: CreateStateMachineDto): StateMachineDto
}