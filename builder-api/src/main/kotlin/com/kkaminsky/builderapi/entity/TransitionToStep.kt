package com.kkaminsky.builderapi.entity

import com.kkaminsky.builderapi.dto.DirectionType
import com.kkaminsky.builderapi.entity.base.BaseUUIDEntity
import javax.persistence.*

@Entity
@Access(value= AccessType.FIELD)
data class TransitionToStep(

    @field:ManyToOne
    @field:JoinColumn(name = "transition_id", nullable = false)
    var transition: Transition,

    @field:ManyToOne
    @field:JoinColumn(name = "step_id", nullable = false)
    var step: Step,


    var directionType: DirectionType
): BaseUUIDEntity()