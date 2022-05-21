package com.ingins.transitionbuilder.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp


import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.time.Instant
import javax.persistence.*

@MappedSuperclass
@Access(value = AccessType.FIELD)
abstract class BaseEntity<T : Serializable> : Persistable<T> {

    @field:Id
    private var id: T
    override fun getId(): T {
        return id
    }

    @field:CreationTimestamp
    @field:Column(name = "create_time")
    var createTime: Instant = Instant.now()

    @field:UpdateTimestamp
    @field:Column(name = "update_time")
    var updateTime: Instant = Instant.now()

    constructor() {
        id = idGenerator()
        createTime = Instant.now()
        updateTime = Instant.now()
    }

    constructor(id: T) : this() {
        this.id = id
    }

    abstract fun idGenerator(): T


    override fun isNew(): Boolean {
        return id == null
    }

    override fun toString(): String {
        return "BaseEntity(id=$id, createTime=$createTime, updateTime=$updateTime, isNew=$isNew)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as BaseEntity<*>
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}