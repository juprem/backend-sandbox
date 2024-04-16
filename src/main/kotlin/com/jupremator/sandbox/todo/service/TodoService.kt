package com.jupremator.sandbox.todo.service

import com.jupremator.sandbox.exception.NoSuchTodoException
import org.springframework.stereotype.Service
import com.jupremator.sandbox.todo.dao.TodoRepository
import com.jupremator.sandbox.todo.model.Todo
import com.jupremator.sandbox.todo.model.TodoEntity
import com.jupremator.sandbox.todo.model.TodoMapper

@Service
class TodoService(
        private val todoRepository: TodoRepository,
        private val todoMapper: TodoMapper,
) {
    fun getOne(id: String): Todo {
        return todoMapper.toModel(todoRepository.findById(id).orElseThrow { throw NoSuchTodoException(id) })
    }

    fun getAll(): List<Todo> = todoMapper.toModel(todoRepository.findAll())
}