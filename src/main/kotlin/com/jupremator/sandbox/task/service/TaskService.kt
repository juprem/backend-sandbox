package com.jupremator.sandbox.task.service

import com.jupremator.sandbox.exception.NoSuchTaskException
import com.jupremator.sandbox.exception.NoSuchTodoException
import com.jupremator.sandbox.task.dao.TaskRepository
import com.jupremator.sandbox.task.model.*
import com.jupremator.sandbox.todo.dao.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskService(
        private val taskRepository: TaskRepository,
        private val todoRepository: TodoRepository,
        private val taskMapper: TaskMapper,
) {
    @Transactional(readOnly = true)
    fun getByTodoId(todoId: String): List<Task> = taskMapper.toModel(taskRepository.findAllByTodoId(todoId))

    @Transactional(readOnly = true)
    fun getById(id: String): Task {
        val task = taskRepository.findById(id).orElseThrow { NoSuchTaskException(id) }

        return taskMapper.toModel(task)
    }

    @Transactional
    fun deleteById(id: String) {
        taskRepository.deleteById(id)
    }

    @Transactional
    fun createTask(id: String, task: TaskCreate): Task {
        todoRepository.findById(id).orElseThrow { NoSuchTodoException(id) }

        val taskEntity = taskRepository.save(taskMapper.toEntity(task, id))

        return taskMapper.toModel(taskEntity)
    }

    @Transactional
    fun updateTask(id: String, taskUpdate: TaskUpdate): Task {
        val task = taskRepository.findById(id).orElseThrow { NoSuchTaskException(id) }

        task.apply {
            description = taskUpdate.description
            name = taskUpdate.name
            done = taskUpdate.done
        }

        return taskMapper.toModel(task)
    }
}