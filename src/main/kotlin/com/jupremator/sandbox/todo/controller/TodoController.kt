package com.jupremator.sandbox.todo.controller

import com.jupremator.sandbox.logging.LogUserAction
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.jupremator.sandbox.todo.model.TodoEntity
import com.jupremator.sandbox.todo.model.TodoMapper
import com.jupremator.sandbox.todo.model.TodoView
import com.jupremator.sandbox.todo.service.TodoService

@RestController
@RequestMapping("/todos")
class TodoController(
        private val todoService: TodoService,
        private val todoMapper: TodoMapper
) {
    @GetMapping("/{id}")
    @LogUserAction
    fun getOne(@PathVariable id: String): TodoView = todoMapper.toView(todoService.getOne(id))

    @GetMapping
    @LogUserAction
    fun getAll(): ResponseEntity<List<TodoView>> = ResponseEntity.ok(todoMapper.toView(todoService.getAll()))
}