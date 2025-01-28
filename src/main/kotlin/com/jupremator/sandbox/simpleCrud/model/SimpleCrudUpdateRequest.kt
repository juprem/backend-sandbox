package com.jupremator.sandbox.simpleCrud.model

data class SimpleCrudUpdateRequest(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
)
