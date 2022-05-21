package com.kkaminsky.builderapi.mapper

import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import com.kkaminsky.builderapi.entity.StateMachine
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface StateMachineMapper {

    fun map(entity: StateMachine): StateMachineDto
}