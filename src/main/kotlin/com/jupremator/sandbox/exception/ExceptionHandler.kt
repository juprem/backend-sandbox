package com.jupremator.sandbox.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.net.URI

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }

    @ExceptionHandler
    fun handleWithNotFound(ex: NoSuchException, request: WebRequest?): ProblemDetail =
            build(HttpStatus.NOT_FOUND, ex.message ?: "Task not found", ex)

    @ExceptionHandler
    fun handleWithBadRequest(ex: InvalidException, request: WebRequest?): ProblemDetail =
            build(HttpStatus.BAD_REQUEST, ex.message ?: "Bad request", ex)

    private fun build(httpStatus: HttpStatusCode, detail: String, ex: BusinessException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, detail)
        problemDetail.title = ex.title()
        problemDetail.type = URI("http://localhost:3000/${ex.type()}")
        problemDetail.setProperty("code", ex.code())
        problemDetail.setProperty("case", ex.case() ?: "")
        problemDetail.setProperty("id", ex.id() ?: "")
        return problemDetail
    }
}
