package com.jupremator.sandbox.todo.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
data class TodoEntity(
        @Id @GeneratedValue(strategy = GenerationType.UUID)
        val id: String,
        var name: String,
        var description: String?,
        @Column(name = "created_time")
        val createdTime: LocalDateTime,
) {
        companion object {
                fun build(id: String): TodoEntity =
                        TodoEntity(id, "", "", LocalDateTime.now())
        }
}
