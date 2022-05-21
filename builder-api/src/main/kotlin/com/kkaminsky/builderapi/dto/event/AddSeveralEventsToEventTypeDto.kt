package com.kkaminsky.builderapi.dto.event

import java.util.*

class AddSeveralEventsToEventTypeDto(
    val eventIds: List<UUID>,
    val eventTypeId: UUID
)