package com.jupremator.sandbox.exception
abstract class BusinessException: Exception {

    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
            message,
            cause,
            enableSuppression,
            writableStackTrace,
    )

    abstract fun type(): String
    abstract fun title(): String
    abstract fun code(): String
    abstract fun case(): String?
    abstract fun id(): String?
}

