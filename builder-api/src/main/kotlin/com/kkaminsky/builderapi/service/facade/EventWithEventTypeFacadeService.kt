package com.kkaminsky.builderapi.service.facade

import com.kkaminsky.builderapi.dto.eventtype.CreateEventTypeWithEventDto
import com.kkaminsky.builderapi.dto.eventtype.EventTypeWithEventsDto
import java.util.*

interface EventWithEventTypeFacadeService {
    fun createEventTypeWithEventsDto(model: CreateEventTypeWithEventDto): EventTypeWithEventsDto
    fun getEventTypesWithEvents(stateMachineId: UUID): List<EventTypeWithEventsDto>
}