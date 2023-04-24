package ru.skypro.homework8.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import ru.skypro.homework8.exceptions.employeeExceptions.EmployeeAlreadyAddedException;
import ru.skypro.homework8.exceptions.employeeExceptions.EmployeeNotFoundException;
import ru.skypro.homework8.exceptions.employeeExceptions.EmployeeStorageIsFullException;

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

    @org.springframework.web.bind.annotation.ExceptionHandler(DepartmentIntervalException.class)
    public ResponseEntity<String> departmentIntervalExceptionHandler(DepartmentIntervalException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SalaryNegativeException.class)
    public ResponseEntity<String> salaryNegativeExceptionHandler(SalaryNegativeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());
    }
}
