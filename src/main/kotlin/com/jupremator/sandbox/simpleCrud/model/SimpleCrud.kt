package com.jupremator.sandbox.simpleCrud.model

import jakarta.persistence.*

@Entity
@Table(name = "simple_crud")
class SimpleCrud(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SimpleCrud

        return id == other.id
    }

    override fun hashCode(): Int = id
}
