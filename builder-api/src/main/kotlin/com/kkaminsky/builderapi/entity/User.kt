package com.kkaminsky.builderapi.entity

import com.kkaminsky.builderapi.entity.base.BaseUUIDEntity
import javax.persistence.Access
import javax.persistence.AccessType
import javax.persistence.Entity

@Entity
@Access(value= AccessType.FIELD)
data class User(
    var username: String?,
    var telegramId: String
) : BaseUUIDEntity()


