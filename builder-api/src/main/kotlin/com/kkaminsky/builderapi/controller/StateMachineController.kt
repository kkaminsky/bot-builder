package com.kkaminsky.builderapi.controller

import com.kkaminsky.builderapi.dto.statemachine.StateMachineDto
import com.kkaminsky.builderapi.service.StateMachineService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/statemachine")
class StateMachineController(
    private val stateMachineService: StateMachineService
) {

    @GetMapping("/get")
    fun getStateMachines(@RequestParam userId: UUID): List<StateMachineDto>{
        return stateMachineService.getAllStateMachines(userId)
    }

}