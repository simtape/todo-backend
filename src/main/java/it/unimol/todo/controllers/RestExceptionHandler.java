package it.unimol.todo.controllers;

import it.unimol.todo.models.response.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // handle RuntimeException

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleHttpForbiddenException(RuntimeException ex) {
        return new ResponseEntity<>(Response.message(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}