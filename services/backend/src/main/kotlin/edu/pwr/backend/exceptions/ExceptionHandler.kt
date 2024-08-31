package edu.pwr.backend.exceptions
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice(annotations = [RestController::class])
class ExceptionHandler {

    @ExceptionHandler(value= [EntityNotFoundException::class])
    @ResponseBody
    fun handleEntityNotFoundException(
        ex: EntityNotFoundException, request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse("No nic kurwa, no nic", ex.message ?: "Entity not found"),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleGeneralException(
        ex: Exception, request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse("Haha, wyjebało produkcję", ex.message ?: "An error occurred"),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}

data class ErrorResponse(
    val error: String,
    val message: String
)
