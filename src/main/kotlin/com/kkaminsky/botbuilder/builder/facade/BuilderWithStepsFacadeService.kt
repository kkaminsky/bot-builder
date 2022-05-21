package com.kkaminsky.builderapi.builder.facade

import org.springframework.statemachine.StateMachine
import java.util.*

interface BuilderWithStepsFacadeService {
    fun buildStateMachineForUser(stateMachineId: UUID): StateMachine<String, String>
}