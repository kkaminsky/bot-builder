package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.step.AddEventToStepDto
import com.kkaminsky.builderapi.dto.event.CreateEventDto
import com.kkaminsky.builderapi.dto.event.CreateSeveralEventsDto
import com.kkaminsky.builderapi.dto.event.EventDto
import com.kkaminsky.builderapi.dto.step.SetSeveralEventsToStepDto
import java.util.*

interface EventService {
    fun getEvent(eventId: UUID): EventDto
    fun getEventsForStep(stepId: UUID): List<EventDto>
    fun getEventsForSteps(steps: List<UUID>): Map<UUID,List<EventDto>>
    fun getEventsForEventTypeId(eventTypeId: UUID): List<EventDto>
    fun getEventsForEventTypeIds(eventTypeIds: List<UUID>): Map<UUID, List<EventDto>>
    fun createEvent(createEventDto: CreateEventDto): EventDto
    fun createSeveralEvents(model: CreateSeveralEventsDto): List<EventDto>
    fun addEventToStep(model: AddEventToStepDto): EventDto
    fun setSeveralEventsToStep(model: SetSeveralEventsToStepDto): List<EventDto>
}