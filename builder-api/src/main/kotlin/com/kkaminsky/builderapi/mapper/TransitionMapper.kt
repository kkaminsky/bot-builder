package com.kkaminsky.builderapi.mapper

import com.kkaminsky.builderapi.dto.transition.TransitionDto
import com.kkaminsky.builderapi.entity.Transition
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface TransitionMapper {
    fun map(entity: Transition): TransitionDto
}