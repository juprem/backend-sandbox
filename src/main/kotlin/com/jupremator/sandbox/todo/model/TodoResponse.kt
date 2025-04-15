package com.jupremator.sandbox.todo.model

import java.time.LocalDateTime

data class TodoResponse(
        val id: String,
        val name: String,
        val isDone: Boolean,
        val createdTime: LocalDateTime,
        val dueDate: LocalDateTime,
        val priority: PriorityEnum,
        val isArchived: Boolean,
        val tags: List<String>,
        val description: String?,
) {
        fun getIsArchived() = isArchived
        fun getIsDone() = isDone
}
