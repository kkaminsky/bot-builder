package com.kkaminsky.builderapi.controller

import com.kkaminsky.builderapi.dto.transition.CreateTransitionWithStepsDto
import com.kkaminsky.builderapi.dto.transition.EditTransitionDto
import com.kkaminsky.builderapi.dto.transition.TransitionWithStepsAndEventsDto
import com.kkaminsky.builderapi.service.facade.TransitionWithStepsFacade
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/transition")
class TransitionController(
    private val transitionWithStepsFacade: TransitionWithStepsFacade
){

    @GetMapping("/get")
    fun getTransitions(@RequestParam stateMachineId: UUID): List<TransitionWithStepsAndEventsDto> {
        return transitionWithStepsFacade.getTransitionsWithSteps(stateMachineId)
    }

    @PostMapping("/edit")
    fun editTransition(@RequestBody dto: EditTransitionDto): TransitionWithStepsAndEventsDto {
        return transitionWithStepsFacade.editTransitionWithSteps(dto)
    }

    @PostMapping("/create")
    fun createTransition(@RequestBody dto: CreateTransitionWithStepsDto): TransitionWithStepsAndEventsDto {
        return transitionWithStepsFacade.createTransitionWithSteps(dto)
    }

}