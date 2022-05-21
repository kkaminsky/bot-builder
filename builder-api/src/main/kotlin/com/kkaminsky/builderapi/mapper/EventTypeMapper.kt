package com.kkaminsky.builderapi.mapper

import com.kkaminsky.builderapi.dto.eventtype.EventTypeDto
import com.kkaminsky.builderapi.entity.EventType
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface EventTypeMapper {
    fun map(entity: EventType): EventTypeDto
}