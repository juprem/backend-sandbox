package todo.model

import java.time.LocalDateTime

data class Todo(
        val id: String,
        val name: String,
        val description: String?,
        val createdTime: LocalDateTime
)
