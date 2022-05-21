package com.kkaminsky.builderapi.entity

import com.kkaminsky.builderapi.entity.base.BaseUUIDEntity
import javax.persistence.*

@Entity
@Access(value= AccessType.FIELD)
data class Transition(

    @field:ManyToOne
    @field:JoinColumn(name = "state_machine_id", nullable = false)
    var stateMachine: StateMachine,
): BaseUUIDEntity()