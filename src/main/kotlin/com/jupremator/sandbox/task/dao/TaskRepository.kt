package com.jupremator.sandbox.task.dao

import com.jupremator.sandbox.task.model.TaskEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface TaskRepository : JpaRepository<TaskEntity, String> {
    @EntityGraph(attributePaths = ["todo"])
    fun findAllByTodoId(todoId: String): List<TaskEntity>
}