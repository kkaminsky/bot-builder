package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.StepWithEventsDto
import com.kkaminsky.builderapi.mapper.StepMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class StepWithEventsFacadeServiceImpl(
    private val stepService: StepService,
    private val eventService: EventService,
    private val userService: UserService,
    private val stateMachineService: StateMachineService,
    private val stepMapper: StepMapper
) : StepWithEventsFacadeService {

    @Transactional(readOnly = true)
    override fun getSteps(userId: UUID): List<StepWithEventsDto> {
        val stateMachine = stateMachineService.getStateMachineRandom(userId)
        val steps = stepService.getAllSteps(stateMachine.id)
        val events = eventService.getEventsForSteps(steps.map { it.id })
        return steps.map { stepMapper.map(it, events[it.id] ?: listOf()) }
    }
}