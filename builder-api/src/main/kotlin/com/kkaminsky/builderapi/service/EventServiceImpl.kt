package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.step.AddEventToStepDto
import com.kkaminsky.builderapi.dto.event.CreateEventDto
import com.kkaminsky.builderapi.dto.event.CreateSeveralEventsDto
import com.kkaminsky.builderapi.dto.event.EventDto
import com.kkaminsky.builderapi.dto.step.SetSeveralEventsToStepDto
import com.kkaminsky.builderapi.entity.Event
import com.kkaminsky.builderapi.entity.EventToStep
import com.kkaminsky.builderapi.mapper.EventMapper
import com.kkaminsky.builderapi.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class EventServiceImpl(
    private val eventToStepRepository: EventToStepRepository,
    private val eventRepository: EventRepository,
    private val eventTypeRepository: EventTypeRepository,
    private val eventMapper: EventMapper,
    private val stepRepository: StepRepository
) : EventService {

    @Transactional(readOnly = true)
    override fun getEvent(eventId: UUID): EventDto {
        return eventRepository.findById(eventId).get().let(eventMapper::map)
    }

    @Transactional(readOnly = true)
    override fun getEventsForStep(stepId: UUID): List<EventDto> {
        return eventToStepRepository.findByStepId(stepId).map { eventMapper.map(it.event) }
    }

    @Transactional(readOnly = true)
    override fun getEventsForSteps(steps: List<UUID>): Map<UUID, List<EventDto>> {
        return eventToStepRepository.findByStepIdIn(steps)
            .groupBy({ it.step.id }, { eventMapper.map(it.event) })
    }

    @Transactional(readOnly = true)
    override fun getEventsForEventTypeId(eventTypeId: UUID): List<EventDto> {
        return eventRepository.findByEventTypeId(eventTypeId).map(eventMapper::map)
    }

    @Transactional
    override fun createEvent(createEventDto: CreateEventDto): EventDto {
        val eventType = eventTypeRepository.findById(createEventDto.eventTypeId).get()
        return eventRepository.save(Event(text = createEventDto.text,eventType = eventType))
            .let(eventMapper::map)
    }

    override fun createSeveralEvents(model: CreateSeveralEventsDto): List<EventDto> {
        val eventType = eventTypeRepository.findById(model.eventTypeId).get()
        return eventRepository.saveAll(model.texts.map { Event(
            text = it,
            eventType = eventType
        ) }).map(eventMapper::map)
    }

    @Transactional
    override fun addEventToStep(model: AddEventToStepDto): EventDto {
        val event = eventRepository.findById(model.eventId).get()
        val step = stepRepository.findById(model.stepId).get()
        return eventToStepRepository.save(EventToStep(event, step))
            .let { eventToStep -> eventMapper.map(eventToStep.event) }
    }


    @Transactional
    override fun setSeveralEventsToStep(model: SetSeveralEventsToStepDto): List<EventDto> {
        val step = stepRepository.findById(model.stepId).get()

        val existedEvents = eventToStepRepository.findByStepId(model.stepId)

        val eventsForDelete = existedEvents.filter { !model.eventIds.contains(it.event.id) }
        eventToStepRepository.deleteAll(eventsForDelete)

        val eventsForAdd = eventRepository.findAllById(
            model.eventIds.filter { !existedEvents.map { it.event.id }.contains(it) })

        return eventToStepRepository.saveAll(eventsForAdd.map { EventToStep(
            event = it,
            step = step
        ) }).map { eventMapper.map(it.event) }
    }

    @Transactional(readOnly = true)
    override fun getEventsForEventTypeIds(eventTypeIds: List<UUID>): Map<UUID, List<EventDto>> {
        return eventRepository.findByEventTypeIdIn(eventTypeIds).groupBy({ it.eventType.id }, { eventMapper.map(it) })
    }
}