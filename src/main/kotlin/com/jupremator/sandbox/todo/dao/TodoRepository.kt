package com.jupremator.sandbox.todo.dao

import com.jupremator.sandbox.todo.model.TodoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<TodoEntity, String> {
}