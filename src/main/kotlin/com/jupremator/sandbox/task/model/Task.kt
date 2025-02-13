package com.jupremator.sandbox.task.model

import com.jupremator.sandbox.todo.model.PriorityEnum
import com.jupremator.sandbox.todo.model.Todo
import java.time.LocalDateTime

data class Task(
        val id: String,
        val name: String,
        val description: String,
        val todo: Todo,
        val status: StatusEnum,
        val priority: PriorityEnum,
        val createdDate: LocalDateTime,
        val tags: List<String>
)
