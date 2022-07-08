package com.algosoft.challenge.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ApplicationExceptionHandler> handleNotFoundExceptions(Exception ex, WebRequest request) {
        ApplicationExceptionHandler applicationExceptionHandler = new ApplicationExceptionHandler(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));
        return new ResponseEntity<>(applicationExceptionHandler, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorException.class)
    public final ResponseEntity<ApplicationExceptionHandler> handleErrorExceptions(Exception ex, WebRequest request) {
        ApplicationExceptionHandler applicationExceptionHandler = new ApplicationExceptionHandler(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));
        return new ResponseEntity<>(applicationExceptionHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ApplicationExceptionHandler> handleConstraintValidationException(ConstraintViolationException ex, WebRequest request) {
        ApplicationExceptionHandler applicationExceptionHandler = new ApplicationExceptionHandler(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));
        return new ResponseEntity<>(applicationExceptionHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApplicationExceptionHandler> handleAllExceptions(Exception ex, WebRequest request) {
        ApplicationExceptionHandler applicationExceptionHandler = new ApplicationExceptionHandler(LocalDateTime.now(), ex.getMessage(), List.of(request.getDescription(false)));
        return new ResponseEntity<>(applicationExceptionHandler, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(t -> String.format("%s: %s", ((FieldError) t).getField(), t.getDefaultMessage())).collect(Collectors.toList());
        ApplicationExceptionHandler applicationExceptionHandler = new ApplicationExceptionHandler(LocalDateTime.now(), "Validation failed", errors);
        return new ResponseEntity<>(applicationExceptionHandler, HttpStatus.BAD_REQUEST);
    }

}
