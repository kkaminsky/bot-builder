package com.ingins.transitionbuilder.entity

import com.ingins.transitionbuilder.entity.BaseEntity
import java.util.*


abstract class BaseUUIDEntity : BaseEntity<UUID> {

    constructor(id : UUID) : super(id)

    constructor() : super()

    override fun idGenerator(): UUID = UUID.randomUUID()
}
