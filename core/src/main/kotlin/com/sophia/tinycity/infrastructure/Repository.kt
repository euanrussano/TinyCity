package com.sophia.tinycity.infrastructure

import com.sophia.tinycity.model.Building

abstract class Repository<T> {

    open val entities = mutableListOf<T>()

    fun findByName(name: String): T{
        return entities.first { entity -> getName(entity) ==  name}
    }

    fun findAll(): List<T>{
        return entities
    }

    abstract fun getName(entity: T): String

}
