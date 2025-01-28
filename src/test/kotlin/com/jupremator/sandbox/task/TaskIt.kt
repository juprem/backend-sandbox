package com.jupremator.sandbox.task

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jupremator.sandbox.annotation.BasicIT
import com.jupremator.sandbox.task.dao.TaskRepository
import com.jupremator.sandbox.task.model.StatusEnum
import com.jupremator.sandbox.task.model.TaskCreate
import com.jupremator.sandbox.task.model.TaskEntity
import com.jupremator.sandbox.task.model.TaskResponse
import com.jupremator.sandbox.todo.dao.TodoRepository
import com.jupremator.sandbox.todo.model.PriorityEnum
import com.jupremator.sandbox.todo.model.TodoEntity
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

@BasicIT
class TaskIt(
    @Autowired private val taskRepository: TaskRepository,
    @Autowired private val todoRepository: TodoRepository,
    @Autowired private val mvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper,
) {
    @AfterEach
    fun after() {
        taskRepository.deleteAll()
        todoRepository.deleteAll()
    }

    @Test
    fun `Given an id in db Should return the task`() {
        val todo =
            todoRepository.save(
                TodoEntity(
                    "",
                    "1Todo",
                    "",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    PriorityEnum.LOW,
                    false,
                    emptyList(),
                ),
            )
        val task =
            taskRepository.save(
                TaskEntity(
                    "",
                    "1Task",
                    "",
                    todo,
                    StatusEnum.TODO,
                    PriorityEnum.LOW,
                    LocalDateTime.now(),
                    emptyList(),
                ),
            )

        val content =
            mvc
                .perform(
                    MockMvcRequestBuilders
                        .get("/tasks/{id}", task.id)
                        .contentType(MediaType.APPLICATION_JSON),
                ).andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .getContentAsString(StandardCharsets.UTF_8)

        val taskResponse = objectMapper.readValue<TaskResponse>(content)

        assertThat(taskResponse.name).isEqualTo(task.name)
        assertThat(taskResponse.description).isEqualTo(task.description)
        assertThat(taskResponse.tags).isEqualTo(task.tags)
        assertThat(taskResponse.priority).isEqualTo(task.priority)
        assertThat(taskResponse.status).isEqualTo(task.status)
    }

    @Test
    fun `Given a new task Should create it`() {
        val todo =
            todoRepository.save(
                TodoEntity(
                    "",
                    "1Todo",
                    "",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    PriorityEnum.LOW,
                    false,
                    emptyList(),
                ),
            )
        val newTask = TaskCreate("CrateTask", "", PriorityEnum.LOW, emptyList())

        val content =
            mvc
                .perform(
                    MockMvcRequestBuilders
                        .post("/tasks/todo/{id}", todo.id)
                        .content(objectMapper.writeValueAsString(newTask))
                        .contentType(MediaType.APPLICATION_JSON),
                ).andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .getContentAsString(StandardCharsets.UTF_8)

        val createdTask = objectMapper.readValue<TaskResponse>(content)
        val entityTask = taskRepository.findById(createdTask.id).get()

        assertThat(createdTask.description).isEqualTo(newTask.description)
        assertThat(createdTask.name).isEqualTo(newTask.name)
        assertThat(createdTask.priority).isEqualTo(newTask.priority)
        assertThat(createdTask.tags).isEqualTo(newTask.tags)
        assertThat(entityTask.todo).isEqualTo(todo)
    }
}
