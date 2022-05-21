package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.eventtype.*
import com.kkaminsky.builderapi.entity.EventType
import com.kkaminsky.builderapi.entity.TransitionToEventType
import com.kkaminsky.builderapi.mapper.EventTypeMapper
import com.kkaminsky.builderapi.repository.EventTypeRepository
import com.kkaminsky.builderapi.repository.StateMachineRepository
import com.kkaminsky.builderapi.repository.TransitionRepository
import com.kkaminsky.builderapi.repository.TransitionToEventTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class EventTypeServiceImpl(
    private val eventTypeRepository: EventTypeRepository,
    private val stateMachineRepository: StateMachineRepository,
    private val eventTypeMapper: EventTypeMapper,
    private val transitionRepository: TransitionRepository,
    private val transitionToEventTypeRepository: TransitionToEventTypeRepository
) : EventTypeService {

    @Transactional
    override fun createEventType(createEventTypeDto: CreateEventTypeDto): EventTypeDto {
        val sm = stateMachineRepository.findById(createEventTypeDto.stateMachineId).get()
        return eventTypeRepository.save(EventType(name = createEventTypeDto.name,stateMachine = sm))
            .let(eventTypeMapper::map)
    }

    @Transactional
    override fun addEventTypeToTransition(model: AddEventTypeToTransitionDto): EventTypeDto {
        val transition = transitionRepository.findById(model.transitionId).get()
        val eventType = eventTypeRepository.findById(model.eventTypeId).get()
        return transitionToEventTypeRepository.save(TransitionToEventType(
            transition = transition,
            eventType = eventType
        )).let { eventTypeMapper.map(it.eventType) }
    }

    @Transactional
    override fun addSeveralEventTypeToTransition(model: AddSeveralEventTypeToTransitionDto): List<EventTypeDto> {
        val transition = transitionRepository.findById(model.transitionId).get()
        val eventTypes = eventTypeRepository.findAllById(model.eventTypeIds)
        return transitionToEventTypeRepository.saveAll(eventTypes.map { TransitionToEventType(
            transition = transition,
            eventType = it
        ) }).map { eventTypeMapper.map(it.eventType) }
    }

    @Transactional
    override fun setEventTypesForTransition(model: SetEventTypeToTransitionDto): List<EventTypeDto> {
        val transition = transitionRepository.findById(model.transitionId).get()

        val existedEventTypes = transitionToEventTypeRepository.findByTransitionIdIn(listOf(transition.id))

        val eventTypesForDelete = existedEventTypes.filter { !model.newEventTypeIds.contains(it.eventType.id) }
        transitionToEventTypeRepository.deleteAll(eventTypesForDelete)

        val eventTypesFotAdd = eventTypeRepository.findAllById(model.newEventTypeIds
            .filter { !existedEventTypes.map { it.eventType.id }.contains(it) })

        return transitionToEventTypeRepository.saveAll(eventTypesFotAdd.map { TransitionToEventType(
            transition = transition,
            eventType = it
        ) }).map { eventTypeMapper.map(it.eventType) }
    }


    @Transactional(readOnly = true)
    override fun getEventTypes(stateMachineId: UUID): List<EventTypeDto> {
        return eventTypeRepository.findByStateMachineId(stateMachineId).map(eventTypeMapper::map)
    }

    @Transactional(readOnly = true)
    override fun getEventTypesByTransitions(transitionIds: List<UUID>): Map<UUID,List<EventTypeDto>> {
        return transitionToEventTypeRepository.findByTransitionIdIn(transitionIds)
            .groupBy( { it.transition.id },{ eventTypeMapper.map(it.eventType)})
    }
}