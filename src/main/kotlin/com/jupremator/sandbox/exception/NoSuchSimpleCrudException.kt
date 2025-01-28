package com.jupremator.sandbox.exception

class NoSuchSimpleCrudException(
    private val id: String,
) : NoSuchException("No such simple crud for id : $id") {
    override fun type() = "no-simple-crud"

    override fun title() = "Simple crud not found"

    override fun code() = "NO_SUCH_SIMPLE_CRUD"

    override fun case() = "NO_SUCH_SIMPLE_CRUD"

    override fun id() = id
}
