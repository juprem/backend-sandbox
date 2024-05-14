package com.jupremator.sandbox.exception

class NoSuchTaskException(private val id: String): NoSuchException("No such task for id : $id") {
    override fun type() = "no-such-task"
    override fun title() = "task not found"
    override fun code() = "NO_SUCH_TASK"
    override fun case() = "NO_SUCH_TASK"
    override fun id() = id
}
