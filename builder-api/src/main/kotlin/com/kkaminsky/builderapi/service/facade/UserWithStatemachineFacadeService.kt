package com.kkaminsky.builderapi.service.facade

import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import com.kkaminsky.builderapi.dto.user.CreateUserDto
import com.kkaminsky.builderapi.dto.user.UserDto

interface UserWithStatemachineFacadeService {
    fun createNewUserAndStateMachine(createUserDto: CreateUserDto): UserDto
    fun getStateMachines(userTelegramId: String): List<StateMachineDto>
}