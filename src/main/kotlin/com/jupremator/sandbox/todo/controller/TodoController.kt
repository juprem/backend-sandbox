package com.jupremator.sandbox.todo.controller

import com.jupremator.sandbox.annotation.ControllerAnnotation
import com.jupremator.sandbox.logging.LogUserAction
import com.jupremator.sandbox.task.model.TaskCreate
import com.jupremator.sandbox.task.model.TaskMapper
import com.jupremator.sandbox.task.model.TaskResponse
import com.jupremator.sandbox.task.service.TaskService
import com.jupremator.sandbox.todo.model.TodoCreate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import com.jupremator.sandbox.todo.model.TodoMapper
import com.jupremator.sandbox.todo.model.TodoUpdate
import com.jupremator.sandbox.todo.model.TodoResponse
import com.jupremator.sandbox.todo.service.TodoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@ControllerAnnotation
class TodoController(
        private val todoService: TodoService,
        private val todoMapper: TodoMapper,
) {
    @GetMapping("/todos/{id}")
    @LogUserAction
    fun getOne(@PathVariable id: String): ResponseEntity<TodoResponse> =
            ResponseEntity.ok(todoMapper.toView(todoService.getOne(id)))

    @GetMapping("/todos")
    @LogUserAction
    fun getAll(): ResponseEntity<List<TodoResponse>> =
        ResponseEntity.ok(todoMapper.toView(todoService.getAll()))


    @PostMapping("/todos")
    @LogUserAction
    fun createTodo(@RequestBody todoCreate: TodoCreate): ResponseEntity<TodoResponse> =
            ResponseEntity.ok(todoMapper.toView(todoService.create(todoCreate)))

    @PutMapping("/todos/{id}")
    @LogUserAction
    fun updateTodo(@PathVariable id: String, todoUpdate: TodoUpdate): ResponseEntity<TodoResponse> =
            ResponseEntity.ok(todoMapper.toView(todoService.updateTodo(id, todoUpdate)))
}