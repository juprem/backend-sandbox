package com.jupremator.sandbox.task.model

import com.jupremator.sandbox.todo.model.Todo

data class Task(
        val id: String,
        val name: String,
        val description: String,
        val done: Boolean,
        val todo: Todo,
)
