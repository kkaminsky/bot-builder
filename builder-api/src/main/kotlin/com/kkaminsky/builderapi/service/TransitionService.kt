package com.kkaminsky.builderapi.service


import com.kkaminsky.builderapi.dto.transition.CreateTransitionDto
import com.kkaminsky.builderapi.dto.transition.TransitionDto
import java.util.*

interface TransitionService {
    fun createTransition(createTransitionDto: CreateTransitionDto): TransitionDto
    fun getTransitions(stateMachineId: UUID): List<TransitionDto>
}
