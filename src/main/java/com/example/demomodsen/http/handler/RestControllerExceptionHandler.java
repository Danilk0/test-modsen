package com.example.demomodsen.http.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice(basePackages = "com.example.demomodsen.http.controller")
public class RestControllerExceptionHandler{

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorMessage> bindException(BindException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorMessage> dataTimeParseException(DateTimeParseException exception) {
        return ResponseEntity
                .status(HttpStatus.valueOf(400))
                .body(new ErrorMessage(exception.getMessage()));
    }


}
