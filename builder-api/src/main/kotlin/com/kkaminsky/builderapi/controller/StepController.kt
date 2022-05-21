package com.kkaminsky.builderapi.controller

import com.kkaminsky.builderapi.dto.step.*
import com.kkaminsky.builderapi.service.StepService
import com.kkaminsky.builderapi.service.facade.StepWithEventsFacadeService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/step")
class StepController(
    private val stepWithEventsFacadeService: StepWithEventsFacadeService,
    private val stepService: StepService
) {

    @GetMapping("/get")
    fun getSteps(@RequestParam stateMachineId: UUID): List<StepWithEventsDto> {
        return stepWithEventsFacadeService.getSteps(stateMachineId)
    }

    @GetMapping("/get/connected")
    fun getConnectedSteps(@RequestParam stepId: UUID): List<StepWithEventsDto> {
        return stepService.getConnectedSteps(stepId)
    }


    @PostMapping("/edit")
    fun editStep(@RequestBody dto: EditStepWithEventsDto): StepWithEventsDto{
        return stepWithEventsFacadeService.editStepWithEvents(dto)
    }

    @PostMapping("/create")
    fun newStep(@RequestBody dto: CreateStepWithEventsDto): StepWithEventsDto{
        return stepWithEventsFacadeService.createStepWithEvents(dto)
    }

}