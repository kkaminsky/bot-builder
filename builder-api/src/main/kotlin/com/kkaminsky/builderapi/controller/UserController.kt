package com.kkaminsky.builderapi.controller

import com.kkaminsky.builderapi.dto.user.CreateUserDto
import com.kkaminsky.builderapi.dto.user.UserDto
import com.kkaminsky.builderapi.service.UserService
import com.kkaminsky.builderapi.service.facade.UserWithStatemachineFacadeService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val userWithStatemachineFacadeService: UserWithStatemachineFacadeService
) {

    @GetMapping("/get/id")
    fun getUser(@RequestParam id: UUID): UserDto {
        return userService.getUser(id)
    }

    @GetMapping("/get/telegramId")
    fun getUser(@RequestParam telegramId: String): UserDto {
        return userService.getUser(telegramId)
    }

    @GetMapping("/get/all")
    fun getAllUsers(): List<UserDto> {
        return userService.getAllUsers()
    }

    @PostMapping("/init")
    fun createUser(@RequestBody model: CreateUserDto): UserDto {
        return userWithStatemachineFacadeService.createNewUserAndStateMachine(model)
    }
}