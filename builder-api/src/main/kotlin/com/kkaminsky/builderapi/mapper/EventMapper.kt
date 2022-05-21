package com.kkaminsky.builderapi.mapper

import com.kkaminsky.builderapi.dto.event.EventDto
import com.kkaminsky.builderapi.entity.Event
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface EventMapper {
    fun map(entity: Event): EventDto
}