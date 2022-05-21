package com.kkaminsky.builderapi.service

import com.kkaminsky.builderapi.dto.StepWithEventsDto
import java.util.*

interface StepWithEventsFacadeService {
    fun getSteps(userId: UUID): List<StepWithEventsDto>
}