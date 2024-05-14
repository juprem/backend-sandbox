package com.jupremator.sandbox.todo.model

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import java.time.LocalDateTime

@Mapper(componentModel = "spring")
interface TodoMapper {

    fun toEntity(todo: Todo): TodoEntity

    @Mapping(target = "id", constant = "")
    @Mapping(target = "createdTime", source = "createDate")
    fun toEntity(todoCreate: TodoCreate, createDate: LocalDateTime): TodoEntity

    fun toModel(todoEntity: TodoEntity): Todo

    fun toModel(todoEntity: List<TodoEntity>): List<Todo>

    fun toModel(todoView: TodoView): Todo

    @Mapping(target = "id", constant = "")
    fun toModel(todoCreate: TodoCreate): Todo

    fun toView(todo: List<Todo>): List<TodoView>

    fun toView(todo: Todo): TodoView
}