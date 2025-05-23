package com.jupremator.sandbox.task.model

import com.jupremator.sandbox.todo.model.PriorityEnum
import java.time.LocalDateTime

data class TaskUpdate(
        val name: String,
        val description: String,
        val status: StatusEnum,
        val priority: PriorityEnum,
        val createdDate: LocalDateTime,
        val tags: List<String>
)
