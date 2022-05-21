package com.kkaminsky.botbuilder.builder.facade

import org.springframework.statemachine.StateMachine
import java.util.*

interface BuilderWithStepsFacadeService {
    fun buildStateMachine(stateMachineId: UUID): StateMachine<String, String>
}