package com.jupremator.sandbox.todo.model

import java.time.LocalDateTime

data class TodoView(
        val id: String,
        val name: String,
        val description: String?,
        val createdTime: LocalDateTime
)
