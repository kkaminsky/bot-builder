package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.DirectionType
import com.kkaminsky.builderapi.dto.step.*
import com.kkaminsky.builderapi.dto.transition.AddStepToTransitionDto
import com.kkaminsky.builderapi.entity.Step
import com.kkaminsky.builderapi.entity.TransitionToStep
import com.kkaminsky.builderapi.mapper.StepMapper
import com.kkaminsky.builderapi.repository.StateMachineRepository
import com.kkaminsky.builderapi.repository.StepRepository
import com.kkaminsky.builderapi.repository.TransitionRepository
import com.kkaminsky.builderapi.repository.TransitionToStepRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class StepServiceImpl(
    private val stepRepository: StepRepository,
    private val stepMapper: StepMapper,
    private val eventService: EventService,
    private val stateMachineRepository: StateMachineRepository,
    private val transitionRepository: TransitionRepository,
    private val transitionToStepRepository: TransitionToStepRepository
) : StepService {

    @Transactional(readOnly = true)
    override fun getAllSteps(stateMachineId: UUID): List<StepDto> {
        return stepRepository.findByStateMachineId(stateMachineId).map(stepMapper::map)
    }

    @Transactional(readOnly = true)
    override fun getStartStep(stateMachineId: UUID): StepDto {
        return stepRepository.findByStateMachineIdAndIsStart(stateMachineId,true).let(stepMapper::map)
    }

    @Transactional(readOnly = true)
    override fun getStep(stepId: UUID): StepDto {
        return stepRepository.findById(stepId).get().let(stepMapper::map)
    }

    @Transactional
    override fun editStep(model: EditStepDto): StepDto {
        val step = stepRepository.findById(model.id).get()
        step.text = model.newText
        return stepRepository.save(step).let(stepMapper::map)
    }

    @Transactional(readOnly = true)
    override fun getConnectedSteps(stepId: UUID): List<StepWithEventsDto> {
        val transitions = transitionToStepRepository.findByStepId(stepId)
        val connectedSteps = transitionToStepRepository.findByTransitionIdIn(transitions.map { it.transition.id })
            .map { stepMapper.map(it.step) }.toSet()
        val events = eventService.getEventsForSteps(connectedSteps.map { it.id })
        return connectedSteps.map { stepMapper.map(it, events[it.id] ?: listOf()) }
    }

    @Transactional
    override fun createStep(createStepDto: CreateStepDto): StepDto {
        val sm = stateMachineRepository.findById(createStepDto.stateMachineId).get()
        return stepRepository.save(Step(text = createStepDto.text,stateMachine = sm, isStart = createStepDto.isStart))
            .let(stepMapper::map)
    }

    @Transactional
    override fun addStepToTransition(model: AddStepToTransitionDto): StepDto {
        val step = stepRepository.findById(model.stepId).get()
        val transition = transitionRepository.findById(model.transitionId).get()
        return transitionToStepRepository.save(TransitionToStep(
            step = step,
            transition = transition,
            directionType = model.directionType
        )).let { stepMapper.map(it.step) }
    }

    @Transactional
    override fun setNewStepForTransition(model: SetNewStepForTransitionDto): StepDto {
        val stepForChange = transitionToStepRepository.findByTransitionIdAndDirectionType(
            transitionId = model.transitionId,
            directionType = model.directionType
        )
        return if (stepForChange.step.id == model.newStepId){
            stepForChange.let { stepMapper.map(it.step) }
        } else {
            transitionToStepRepository.delete(stepForChange)
            val step = stepRepository.findById(model.newStepId).get()
            transitionToStepRepository.save(
                TransitionToStep(
                    transition = stepForChange.transition,
                    step = step,
                    directionType = model.directionType
                )
            ).let { stepMapper.map(it.step) }
        }

    }

    @Transactional(readOnly = true)
    override fun getStepsByTransition(transitionIds: List<UUID>): Map<UUID, List<Pair<StepDto, DirectionType>>> {
        return transitionToStepRepository.findByTransitionIdIn(transitionIds)
            .groupBy ({ it.transition.id },{ stepMapper.map(it.step) to it.directionType})
    }
}