package com.jupremator.sandbox.task.model

import com.jupremator.sandbox.todo.model.TodoEntity
import jakarta.persistence.*

@Entity
@Table(name = "task")
data class TaskEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        val id: String,
        var description: String,
        var done: Boolean,
        var name: String,
        @JoinColumn(name = "todo_id", nullable = false)
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        val todo: TodoEntity
)
