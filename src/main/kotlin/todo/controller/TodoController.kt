package todo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import todo.model.TodoEntity
import todo.model.TodoMapper
import todo.model.TodoView
import todo.service.TodoService

@RestController
@RequestMapping("/todos")
class TodoController(
        private val todoService: TodoService,
        private val todoMapper: TodoMapper
) {
    @GetMapping("/{id}")
    fun getOne(@PathVariable id: String): TodoView = todoMapper.toView(todoService.getOne(id))

    @GetMapping
    fun getAll(): List<TodoView> = todoMapper.toView(todoService.getAll())
}