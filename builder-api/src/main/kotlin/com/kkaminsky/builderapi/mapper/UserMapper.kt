package com.kkaminsky.builderapi.mapper

import com.kkaminsky.builderapi.dto.user.UserDto
import com.kkaminsky.builderapi.entity.User
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface UserMapper {

    fun map(entity: User): UserDto
}