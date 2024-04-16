package com.jupremator.sandbox.logging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class UserActionLogger {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(Companion::class.java)
    }

    @Before("@annotation(com.jupremator.sandbox.logging.LogUserAction)")
    fun logAction(joinPoint: JoinPoint) {
        try {
            val clazz = joinPoint.target.javaClass

            LOGGER.info("User call method <${joinPoint.signature.name}> in <${clazz.simpleName}>")
        } catch (e: Exception) {
            LOGGER.warn("Error when log user action", e)
        }
    }
}
