package com.kkaminsky.builderapi.service.facade

import com.kkaminsky.builderapi.dto.event.CreateEventDto
import com.kkaminsky.builderapi.dto.event.CreateSeveralEventsDto
import com.kkaminsky.builderapi.dto.eventtype.CreateEventTypeDto
import com.kkaminsky.builderapi.dto.eventtype.CreateEventTypeWithEventDto
import com.kkaminsky.builderapi.dto.eventtype.EventTypeWithEventsDto
import com.kkaminsky.builderapi.service.EventService
import com.kkaminsky.builderapi.service.EventTypeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class EventWithEventTypeFacadeServiceImpl(
    private val eventTypeService: EventTypeService,
    private val eventService: EventService
) : EventWithEventTypeFacadeService {

    @Transactional
    override fun createEventTypeWithEventsDto(model: CreateEventTypeWithEventDto): EventTypeWithEventsDto {
        val eventType = eventTypeService.createEventType(
            CreateEventTypeDto(
                name = model.name,
                stateMachineId = model.stateMachineId
            )
        )

        val events = eventService.createSeveralEvents(
            CreateSeveralEventsDto(
                texts = model.eventTexts,
                stateMachineId = model.stateMachineId,
                eventTypeId = eventType.id
            )
        )

        return EventTypeWithEventsDto(
            id = eventType.id,
            name = eventType.name,
            stateMachine = eventType.stateMachine,
            events = events
        )
    }

    @Transactional(readOnly = true)
    override fun getEventTypesWithEvents(stateMachineId: UUID): List<EventTypeWithEventsDto> {
        val eventTypes = eventTypeService.getEventTypes(stateMachineId)
        val events = eventService.getEventsForEventTypeIds(eventTypes.map { it.id })
        return eventTypes.map {
            EventTypeWithEventsDto(
                id = it.id,
                name = it.name,
                stateMachine = it.stateMachine,
                events[it.id] ?: listOf()
            )
        }
    }
}