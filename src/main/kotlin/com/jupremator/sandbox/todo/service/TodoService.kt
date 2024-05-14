package com.jupremator.sandbox.todo.service

import com.jupremator.sandbox.exception.NoSuchTodoException
import org.springframework.stereotype.Service
import com.jupremator.sandbox.todo.dao.TodoRepository
import com.jupremator.sandbox.task.model.Task
import com.jupremator.sandbox.todo.model.Todo
import com.jupremator.sandbox.todo.model.TodoCreate
import com.jupremator.sandbox.todo.model.TodoMapper
import com.jupremator.sandbox.todo.model.TodoUpdate
import jakarta.transaction.Transactional
import java.time.LocalDateTime

@Service
class TodoService(
        private val todoRepository: TodoRepository,
        private val todoMapper: TodoMapper,
) {
    fun getOne(id: String): Todo {
        return todoMapper.toModel(todoRepository.findById(id).orElseThrow { throw NoSuchTodoException(id) })
    }

    fun getAll(): List<Todo> = todoMapper.toModel(todoRepository.findAll())

    @Transactional
    fun create(todoCreate: TodoCreate): Todo {
        val todo = todoRepository.save(todoMapper.toEntity(todoCreate, LocalDateTime.now()))

        return todoMapper.toModel(todo)
    }

    @Transactional
    fun updateTodo(id: String, todoUpdate: TodoUpdate): Todo {
        val todo = todoRepository.findById(id).orElseThrow { NoSuchTodoException(id) }

        todo.apply {
            name = todoUpdate.name
            description = todoUpdate.description
        }

        return todoMapper.toModel(todo)
    }
}