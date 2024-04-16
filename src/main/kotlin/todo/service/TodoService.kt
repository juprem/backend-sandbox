package todo.service

import exception.NoSuchTodoException
import org.springframework.stereotype.Service
import todo.dao.TodoRepository
import todo.model.Todo
import todo.model.TodoEntity
import todo.model.TodoMapper

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