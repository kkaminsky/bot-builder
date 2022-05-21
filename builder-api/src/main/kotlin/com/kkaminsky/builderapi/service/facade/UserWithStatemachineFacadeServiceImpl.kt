package com.kkaminsky.builderapi.service.facade

import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import com.kkaminsky.builderapi.dto.user.CreateUserDto
import com.kkaminsky.builderapi.dto.user.UserDto
import com.kkaminsky.builderapi.service.StateMachineService
import com.kkaminsky.builderapi.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserWithStatemachineFacadeServiceImpl(
    private val userService: UserService,
    private val baseStateMachineFacadeService: BaseStateMachineFacadeService,
    private val stateMachineService: StateMachineService
) : UserWithStatemachineFacadeService {

    @Transactional
    override fun createNewUserAndStateMachine(createUserDto: CreateUserDto): UserDto {
        val newUser = userService.create(createUserDto)
        baseStateMachineFacadeService.createBaseStatemachine(newUser.id)
        return newUser
    }

    @Transactional(readOnly = true)
    override fun getStateMachines(userTelegramId: String): List<StateMachineDto> {
        val user = userService.getUser(userTelegramId)
        return stateMachineService.getAllStateMachines(userId = user.id)
    }

}