package com.kkaminsky.builderapi.entity

import com.kkaminsky.builderapi.entity.base.BaseUUIDEntity
import javax.persistence.*

@Entity
@Access(value= AccessType.FIELD)
data class TransitionToEventType(

    @field:ManyToOne
    @field:JoinColumn(name = "transition_id", nullable = false)
    var transition: Transition,

    @field:ManyToOne
    @field:JoinColumn(name = "event_type_id", nullable = false)
    var eventType: EventType
): BaseUUIDEntity()