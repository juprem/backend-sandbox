package com.jupremator.sandbox.task.model

import com.jupremator.sandbox.todo.model.TodoEntity
import com.jupremator.sandbox.todo.model.TodoMapper
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(componentModel = "spring", uses = [TodoMapper::class])
abstract class TaskMapper {
    @Named("toTodoEntity")
    fun toTodoEntity(id: String): TodoEntity = TodoEntity.build(id)

    abstract fun toEntity(task: Task): TaskEntity
    @Mapping(source = "todoId", target = "todo", qualifiedByName = ["toTodoEntity"])
    @Mapping(target = "id", constant = "")
    @Mapping(target = "status", constant = "TODO")
    @Mapping(target = "createdDate", expression = "java( LocalDateTime.now() )")
    abstract fun toEntity(task: TaskCreate, todoId: String): TaskEntity
    abstract fun toModel(taskEntity: List<TaskEntity>): List<Task>
    abstract fun toModel(taskEntity: TaskEntity): Task
    abstract fun toView(task: Task): TaskResponse
    abstract fun toView(task: List<Task>): List<TaskResponse>
}