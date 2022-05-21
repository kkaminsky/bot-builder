package com.kkaminsky.builderapi.entity

import com.kkaminsky.builderapi.entity.base.BaseUUIDEntity
import javax.persistence.*

@Entity
@Access(value= AccessType.FIELD)
data class Event(
    var text: String,

    @field:ManyToOne
    @field:JoinColumn(name = "event_type_id", nullable = false)
    var eventType: EventType,

    ): BaseUUIDEntity()