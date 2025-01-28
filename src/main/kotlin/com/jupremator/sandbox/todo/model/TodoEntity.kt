package com.jupremator.sandbox.todo.model

import io.hypersistence.utils.hibernate.type.array.ListArrayType
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.Type
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
class TodoEntity(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    var name: String,
    var description: String?,
    @Column(name = "created_time")
    val createdTime: LocalDateTime,
    @Column(name = "due_date")
    val dueDate: LocalDateTime,
    val priority: PriorityEnum,
    @Column(name = "is_archived")
    val isArchived: Boolean,
    @Type(ListArrayType::class)
    val tags: List<String>,
) {
    companion object {
        fun build(id: String): TodoEntity =
            TodoEntity(id, "", "dazd", LocalDateTime.now(), LocalDateTime.now(), PriorityEnum.LOW, false, emptyList())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as TodoEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        val result = id.hashCode()
        return result
    }

    fun getIsArchived() = isArchived
}

abstract class EntityTTT
