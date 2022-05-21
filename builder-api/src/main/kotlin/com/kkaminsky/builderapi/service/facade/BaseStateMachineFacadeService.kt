package com.kkaminsky.builderapi.service.facade

import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import java.util.*

interface BaseStateMachineFacadeService {
    fun createBaseStatemachine(userId: UUID): StateMachineDto
}