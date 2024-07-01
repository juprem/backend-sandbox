package com.jupremator.sandbox.todo.model

import jakarta.persistence.*
import org.hibernate.Hibernate
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
) {
        companion object {
                fun build(id: String): TodoEntity =
                        TodoEntity(id, "", "", LocalDateTime.now())
        }

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

                other as TodoEntity

                if (id != other.id) return false
                if (name != other.name) return false
                if (description != other.description) return false
                if (createdTime != other.createdTime) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + name.hashCode()
                result = 31 * result + (description?.hashCode() ?: 0)
                result = 31 * result + createdTime.hashCode()
                return result
        }
}
