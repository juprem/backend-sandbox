package com.jupremator.sandbox.todo.model

import java.time.LocalDateTime

data class TodoCreate(
        val name: String,
        val description: String?,
        val dueDate: LocalDateTime,
        val priority: PriorityEnum,
        val tags: List<String>,
)
