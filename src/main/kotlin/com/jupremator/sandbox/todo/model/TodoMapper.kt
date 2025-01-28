package com.jupremator.sandbox.todo.model

import org.mapstruct.*
import java.time.LocalDateTime

@Mapper(componentModel = "spring")
interface TodoMapper {

    fun toEntity(todo: Todo): TodoEntity

    @Mapping(target = "id", constant = "")
    @Mapping(target = "createdTime", source = "createdTime")
    @Mapping(target = "isArchived", constant = "false")
    fun toEntity(todoCreate: TodoCreate, createdTime: LocalDateTime): TodoEntity

    @Mapping(target = "isArchived", ignore = true)
    fun toModel(todoEntity: TodoEntity): Todo

    fun toModel(todoEntity: List<TodoEntity>): List<Todo>

    fun toModel(todoResponse: TodoResponse): Todo

    @Mapping(target = "id", constant = "")
    fun toModel(todoCreate: TodoCreate): Todo

    fun toView(todo: List<Todo>): List<TodoResponse>

    fun toView(todo: Todo): TodoResponse

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun partialUpdate(todo: TodoUpdate, @MappingTarget todoEntity: TodoEntity)
}