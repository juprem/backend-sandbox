package com.jupremator.sandbox.todo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jupremator.sandbox.annotation.BasicIT
import com.jupremator.sandbox.todo.dao.TodoRepository
import com.jupremator.sandbox.todo.model.*
import com.jupremator.sandbox.todo.service.TodoService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

@BasicIT
class TodoIT(
    @Autowired private val todoRepository: TodoRepository,
    @Autowired private val objectMapper: ObjectMapper,
    @Autowired private val mvc: MockMvc,
    @Autowired private val todoService: TodoService
) {
    @AfterEach
    fun after() {
        todoRepository.deleteAll()
    }

    @Nested
    inner class Create {
        @Test
        fun `Given new todo Should add it to the db`() {
            val dueDate = LocalDateTime.now()
            val createTodo = TodoCreate("newTodo", "description", dueDate, PriorityEnum.LOW, listOf("cook"))

            val content =
                mvc
                    .perform(
                        MockMvcRequestBuilders
                            .post("/todos")
                            .content(
                                objectMapper.writeValueAsString(createTodo),
                            ).contentType(MediaType.APPLICATION_JSON)
                            .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader()),
                    ).andExpect(MockMvcResultMatchers.status().isOk)
                    .andReturn()
                    .response
                    .getContentAsString(StandardCharsets.UTF_8)

            val todo = objectMapper.readValue<TodoResponse>(content)

            assertThat(todo.name).isEqualTo(createTodo.name)
            assertThat(todo.description).isEqualTo(createTodo.description)
            assertThat(todo.tags).isEqualTo(createTodo.tags)
            assertThat(todo.dueDate).isEqualTo(dueDate)
            assertThat(todo.priority).isEqualTo(createTodo.priority)
        }
    }

    @Nested
    inner class Get {
        @Test
        fun `Should return all the todos`() {
            todoRepository.save(TodoEntity("", "1Todo", "", LocalDateTime.now(), LocalDateTime.now(), PriorityEnum.LOW, false, emptyList()))

            val content =
                mvc
                    .perform(
                        MockMvcRequestBuilders
                            .get("/todos")
                            .contentType(MediaType.APPLICATION_JSON),
                    ).andExpect(MockMvcResultMatchers.status().isOk)
                    .andReturn()
                    .response
                    .getContentAsString(StandardCharsets.UTF_8)

            val todos = objectMapper.readValue<List<TodoResponse>>(content)

            assertThat(todos.size).isEqualTo(1)
        }

        @Test
        fun `Given an id todo Should return it`() {
            val todo =
                todoRepository.save(
                    TodoEntity("", "1Todo", "", LocalDateTime.now(), LocalDateTime.now(), PriorityEnum.LOW, false, emptyList()),
                )

            val content =
                mvc
                    .perform(
                        MockMvcRequestBuilders
                            .get("/todos/{id}", todo.id)
                            .contentType(MediaType.APPLICATION_JSON),
                    ).andExpect(MockMvcResultMatchers.status().isOk)
                    .andReturn()
                    .response
                    .getContentAsString(StandardCharsets.UTF_8)

            val todoParsed = objectMapper.readValue<TodoResponse>(content)

            assertThat(todoParsed.name).isEqualTo(todo.name)
            assertThat(todoParsed.description).isEqualTo(todo.description)
            assertThat(todoParsed.isArchived).isEqualTo(todo.isArchived)
            assertThat(todoParsed.priority).isEqualTo(todo.priority)
            assertThat(todoParsed.tags).isEqualTo(todo.tags)
        }

        @Test
        fun `Given an id todo not in the db Should return notFound`() {
            mvc
                .perform(
                    MockMvcRequestBuilders
                        .get("/todos/{id}", "aaaa")
                        .contentType(MediaType.APPLICATION_JSON),
                ).andExpect(MockMvcResultMatchers.status().isNotFound)
        }
    }
}
