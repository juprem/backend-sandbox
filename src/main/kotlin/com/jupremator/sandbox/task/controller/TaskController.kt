package com.jupremator.sandbox.task.controller

import com.jupremator.sandbox.annotation.ControllerAnnotation
import com.jupremator.sandbox.logging.LogUserAction
import com.jupremator.sandbox.task.model.TaskCreate
import com.jupremator.sandbox.task.model.TaskMapper
import com.jupremator.sandbox.task.model.TaskResponse
import com.jupremator.sandbox.task.model.TaskUpdate
import com.jupremator.sandbox.task.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@ControllerAnnotation
class TaskController(
    private val taskService: TaskService,
    private val taskMapper: TaskMapper,
) {
    @GetMapping("/tasks/{id}")
    @LogUserAction
    fun getById(
        @PathVariable id: String,
    ): ResponseEntity<TaskResponse> = ResponseEntity.ok(taskMapper.toView(taskService.getById(id)))

    @DeleteMapping("/tasks/{id}")
    @LogUserAction
    fun deleteTask(
        @PathVariable id: String,
    ) = taskService.deleteById(id)

    @PutMapping("/tasks/{id}")
    @LogUserAction
    fun updateTask(
        @PathVariable id: String,
        @RequestBody taskUpdate: TaskUpdate,
    ): ResponseEntity<TaskResponse> = ResponseEntity.ok(taskMapper.toView(taskService.updateTask(id, taskUpdate)))

    @PostMapping("/tasks/todo/{id}")
    @LogUserAction
    fun createTask(@PathVariable id: String, @RequestBody task: TaskCreate): ResponseEntity<TaskResponse> =
        ResponseEntity.ok(taskMapper.toView(taskService.createTask(id, task)))

    @GetMapping("/tasks/todo/{id}")
    @LogUserAction
    fun getTasks(@PathVariable id: String): ResponseEntity<List<TaskResponse>> =
        ResponseEntity.ok(taskMapper.toView(taskService.getByTodoId(id)))


}
