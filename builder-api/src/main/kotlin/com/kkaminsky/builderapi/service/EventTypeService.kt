package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.eventtype.*
import java.util.*

interface EventTypeService {
    fun createEventType(createEventTypeDto: CreateEventTypeDto): EventTypeDto
    fun addEventTypeToTransition(model: AddEventTypeToTransitionDto): EventTypeDto
    fun addSeveralEventTypeToTransition(model: AddSeveralEventTypeToTransitionDto): List<EventTypeDto>
    fun setEventTypesForTransition(model: SetEventTypeToTransitionDto): List<EventTypeDto>
    fun getEventTypes(stateMachineId: UUID): List<EventTypeDto>
    fun getEventTypesByTransitions(transitionIds: List<UUID>):  Map<UUID,List<EventTypeDto>>
}