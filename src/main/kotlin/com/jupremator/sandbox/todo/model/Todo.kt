package com.jupremator.sandbox.todo.model

import java.time.LocalDateTime

data class Todo(
        val id: String?,
        val name: String?,
        val description: String?,
        val createdTime: LocalDateTime?,
        val dueDate: LocalDateTime?,
        val priority: PriorityEnum?,
        val isArchived: Boolean?,
        val tags: List<String>?,
) {
        fun getIsArchived() = isArchived
}
