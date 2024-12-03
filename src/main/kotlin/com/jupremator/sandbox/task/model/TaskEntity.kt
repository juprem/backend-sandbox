package com.jupremator.sandbox.task.model

import com.jupremator.sandbox.todo.model.PriorityEnum
import com.jupremator.sandbox.todo.model.TodoEntity
import io.hypersistence.utils.hibernate.type.array.ListArrayType
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.Type
import java.time.LocalDateTime

@Entity
@Table(name = "task")
class TaskEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    var name: String,
    var description: String,
    @JoinColumn(name = "todo_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    val todo: TodoEntity,
    val status: StatusEnum,
    val priority: PriorityEnum,
    @Column(name = "created_date")
    val createdDate: LocalDateTime,
    @Type(ListArrayType::class)
    val tags: List<String>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as TaskEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        val result = id.hashCode()
        return result
    }
}
