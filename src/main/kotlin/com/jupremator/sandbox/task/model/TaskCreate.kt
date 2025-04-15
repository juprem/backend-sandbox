package com.jupremator.sandbox.task.model

import com.jupremator.sandbox.todo.model.PriorityEnum

data class TaskCreate(
        val name: String,
        val description: String,
        val priority: PriorityEnum,
        val tags: List<String>
)
