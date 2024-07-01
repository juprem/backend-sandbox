package com.jupremator.sandbox.task.model

import com.jupremator.sandbox.todo.model.TodoEntity
import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
@Table(name = "task")
class TaskEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        val id: String,
        var description: String,
        var done: Boolean,
        var name: String,
        @JoinColumn(name = "todo_id", nullable = false)
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        val todo: TodoEntity
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

                other as TaskEntity

                if (id != other.id) return false
                if (description != other.description) return false
                if (done != other.done) return false
                if (name != other.name) return false
                if (todo != other.todo) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + description.hashCode()
                result = 31 * result + done.hashCode()
                result = 31 * result + name.hashCode()
                result = 31 * result + todo.hashCode()
                return result
        }
}
