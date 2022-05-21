package com.kkaminsky.builderapi.entity

import com.kkaminsky.builderapi.entity.base.BaseUUIDEntity
import javax.persistence.*

@Entity
@Access(value= AccessType.FIELD)
data class EventToStep(

    @field:ManyToOne
    @field:JoinColumn(name = "event_id", nullable = false)
    var event: Event,

    @field:ManyToOne
    @field:JoinColumn(name = "step_id", nullable = false)
    var step: Step,
): BaseUUIDEntity()