package com.jupremator.sandbox.todo.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
data class TodoEntity(
        @Id @GeneratedValue(strategy = GenerationType.UUID)
        val id: String,
        val name: String,
        val description: String?,
        @Column(name = "created_time")
        val createdTime: LocalDateTime
)
