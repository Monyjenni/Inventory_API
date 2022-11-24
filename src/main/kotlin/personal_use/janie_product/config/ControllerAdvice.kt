package personal_use.janie_product.config

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus


@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(java.lang.RuntimeException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun employeeNotFoundHandler(ex: java.lang.RuntimeException): String? {
        return ex.localizedMessage
    }

}