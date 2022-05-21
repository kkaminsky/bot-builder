package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.transition.CreateTransitionDto
import com.kkaminsky.builderapi.dto.transition.TransitionDto
import com.kkaminsky.builderapi.entity.Transition
import com.kkaminsky.builderapi.mapper.TransitionMapper
import com.kkaminsky.builderapi.repository.EventTypeRepository
import com.kkaminsky.builderapi.repository.StateMachineRepository
import com.kkaminsky.builderapi.repository.TransitionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TransitionServiceImpl(
    private val transitionRepository: TransitionRepository,
    private val stateMachineRepository: StateMachineRepository,
    private val transitionMapper: TransitionMapper
) : TransitionService {
    @Transactional
    override fun createTransition(createTransitionDto: CreateTransitionDto): TransitionDto {
        val sm = stateMachineRepository.findById(createTransitionDto.stateMachineId).get()
        return transitionRepository.save(Transition(stateMachine = sm)).let(transitionMapper::map)
    }

    @Transactional(readOnly = true)
    override fun getTransitions(stateMachineId: UUID): List<TransitionDto> {
        return transitionRepository.findByStateMachineId(stateMachineId).map(transitionMapper::map)
    }
}