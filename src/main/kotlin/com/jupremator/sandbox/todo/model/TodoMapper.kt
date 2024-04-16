package com.jupremator.sandbox.todo.model

import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface TodoMapper {

    @Mapping(target = "id", constant = "")
    fun toEntity(todo: Todo): TodoEntity

    fun toModel(todoEntity: TodoEntity): Todo

    fun toModel(todoEntity: List<TodoEntity>): List<Todo>

    fun toModel(todoView: TodoView): Todo

    fun toView(todo: List<Todo>): List<TodoView>
    fun toView(todo: Todo): TodoView
}