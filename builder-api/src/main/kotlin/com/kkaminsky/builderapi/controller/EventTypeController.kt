package com.kkaminsky.builderapi.controller

import com.kkaminsky.builderapi.dto.eventtype.CreateEventTypeWithEventDto
import com.kkaminsky.builderapi.dto.eventtype.EventTypeWithEventsDto
import com.kkaminsky.builderapi.service.facade.EventWithEventTypeFacadeService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/eventtype")
class EventTypeController(
    private val eventWithEventTypeFacadeService: EventWithEventTypeFacadeService
){

    @GetMapping("/get")
    fun getEventTypes(@RequestParam stateMachineId: UUID): List<EventTypeWithEventsDto> {
        return eventWithEventTypeFacadeService.getEventTypesWithEvents(stateMachineId)
    }

    @PostMapping("/create")
    fun createEventTypeWithEvents(@RequestBody dto: CreateEventTypeWithEventDto): EventTypeWithEventsDto {
        return eventWithEventTypeFacadeService.createEventTypeWithEventsDto(dto)
    }
}