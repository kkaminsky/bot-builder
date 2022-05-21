package com.kkaminsky.builderapi.mapper

import com.kkaminsky.builderapi.dto.event.EventDto
import com.kkaminsky.builderapi.dto.step.StepDto
import com.kkaminsky.builderapi.dto.step.StepWithEventsDto
import com.kkaminsky.builderapi.entity.Step
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface StepMapper {

    fun map(entity: Step): StepDto

    fun map(step: StepDto, events: List<EventDto>): StepWithEventsDto
}