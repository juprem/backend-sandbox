package com.jupremator.sandbox.task.controller

import com.jupremator.sandbox.logging.LogUserAction
import com.jupremator.sandbox.task.model.TaskMapper
import com.jupremator.sandbox.task.model.TaskUpdate
import com.jupremator.sandbox.task.model.TaskView
import com.jupremator.sandbox.task.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/tasks")
@CrossOrigin
class TaskController(
        private val taskService: TaskService,
        private val taskMapper: TaskMapper
) {

    @GetMapping("/{id}")
    @LogUserAction
    fun getById(@PathVariable id: String): ResponseEntity<TaskView> =
            ResponseEntity.ok(taskMapper.toView(taskService.getById(id)))

    @DeleteMapping("/{id}")
    @LogUserAction
    fun deleteTask(@PathVariable id: String) = taskService.deleteById(id)

    @PutMapping("/{id}")
    @LogUserAction
    fun updateTask(@PathVariable id: String, taskUpdate: TaskUpdate): ResponseEntity<TaskView> =
            ResponseEntity.ok(taskMapper.toView(taskService.updateTask(id, taskUpdate)))

}