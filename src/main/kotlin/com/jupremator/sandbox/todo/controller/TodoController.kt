package com.jupremator.sandbox.todo.controller

import com.jupremator.sandbox.annotation.ControllerAnnotation
import com.jupremator.sandbox.logging.LogUserAction
import com.jupremator.sandbox.task.model.TaskCreate
import com.jupremator.sandbox.task.model.TaskMapper
import com.jupremator.sandbox.task.model.TaskView
import com.jupremator.sandbox.task.service.TaskService
import com.jupremator.sandbox.todo.model.TodoCreate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import com.jupremator.sandbox.todo.model.TodoMapper
import com.jupremator.sandbox.todo.model.TodoUpdate
import com.jupremator.sandbox.todo.model.TodoView
import com.jupremator.sandbox.todo.service.TodoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@ControllerAnnotation
class TodoController(
        private val todoService: TodoService,
        private val taskService: TaskService,
        private val todoMapper: TodoMapper,
        private val taskMapper: TaskMapper,
) {
    @GetMapping("/todos/{id}")
    @LogUserAction
    fun getOne(@PathVariable id: String): ResponseEntity<TodoView> =
            ResponseEntity.ok(todoMapper.toView(todoService.getOne(id)))

    @GetMapping("/todos")
    @LogUserAction
    fun getAll(): ResponseEntity<List<TodoView>> = ResponseEntity.ok(todoMapper.toView(todoService.getAll()))

    @GetMapping("/todos/{id}/tasks")
    @LogUserAction
    fun getTasks(@PathVariable id: String): ResponseEntity<List<TaskView>> =
            ResponseEntity.ok(taskMapper.toView(taskService.getByTodoId(id)))

    @PostMapping("/todos/{id}/tasks")
    @LogUserAction
    fun createTask(@PathVariable id: String, @RequestBody task: TaskCreate): ResponseEntity<TaskView> =
            ResponseEntity.ok(taskMapper.toView(taskService.createTask(id, task)))

    @PostMapping("/todos")
    @LogUserAction
    fun createTodo(@RequestBody todoCreate: TodoCreate): ResponseEntity<TodoView> =
            ResponseEntity.ok(todoMapper.toView(todoService.create(todoCreate)))

    @PutMapping("/todos/{id}")
    @LogUserAction
    fun updateTodo(@PathVariable id: String, todoUpdate: TodoUpdate): ResponseEntity<TodoView> =
            ResponseEntity.ok(todoMapper.toView(todoService.updateTodo(id, todoUpdate)))
}