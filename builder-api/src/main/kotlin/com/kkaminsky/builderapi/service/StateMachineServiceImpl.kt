package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.statemachine.CreateStateMachineDto
import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import com.kkaminsky.builderapi.entity.StateMachine
import com.kkaminsky.builderapi.mapper.StateMachineMapper
import com.kkaminsky.builderapi.repository.StateMachineRepository
import com.kkaminsky.builderapi.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class StateMachineServiceImpl(
    private val stateMachineRepository: StateMachineRepository,
    private val stateMachineMapper: StateMachineMapper,
    private val userRepository: UserRepository
) : StateMachineService {

    @Transactional(readOnly = true)
    override fun getAllStateMachines(userId: UUID): List<StateMachineDto> {
        return stateMachineRepository.findByUserId(userId).map(stateMachineMapper::map)
    }

    @Transactional(readOnly = true)
    override fun getStateMachineRandom(userId: UUID): StateMachineDto {
        return getAllStateMachines(userId).first()
    }

    @Transactional(readOnly = true)
    override fun getStateMachine(stateMachineId: UUID): StateMachineDto {
        return stateMachineRepository.findById(stateMachineId).map(stateMachineMapper::map).get()
    }

    @Transactional
    override fun createStateMachine(model: CreateStateMachineDto): StateMachineDto {
        val user = userRepository.findById(model.userId).get()
        return stateMachineRepository.save(StateMachine(name = model.name,user = user)).let(stateMachineMapper::map)
    }
}