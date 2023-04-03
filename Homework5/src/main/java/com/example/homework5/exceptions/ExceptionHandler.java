package com.example.homework5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> employeeNotFoundExceptionHandler(EmployeeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeAlreadyAddedException.class)
    public ResponseEntity<String> employeeAlreadyAddedExceptionHandler(EmployeeAlreadyAddedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeStorageIsFullException.class)
    public ResponseEntity<String> employeeStorageIsFullExceptionHandler(EmployeeStorageIsFullException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());
    }
}
