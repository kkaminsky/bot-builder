package com.kkaminsky.builderapi.dto.event

import com.kkaminsky.builderapi.dto.eventtype.EventTypeDto
import java.util.*

data class EventDto(
    val id: UUID,
    val text: String,
    val eventType: EventTypeDto
)