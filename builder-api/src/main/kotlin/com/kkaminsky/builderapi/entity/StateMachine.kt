package com.kkaminsky.builderapi.entity

import com.kkaminsky.builderapi.entity.base.BaseUUIDEntity
import javax.persistence.*

@Entity
@Access(value= AccessType.FIELD)
data class StateMachine(
    var name: String,
    @field:ManyToOne
    @field:JoinColumn(name = "user_id", nullable = false)
    var user: User,
) : BaseUUIDEntity()