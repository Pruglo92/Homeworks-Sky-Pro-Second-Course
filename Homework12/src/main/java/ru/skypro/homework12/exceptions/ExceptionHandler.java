package ru.skypro.homework12.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import ru.skypro.homework12.exceptions.departmentExceptions.DepartmentIntervalException;
import ru.skypro.homework12.exceptions.departmentExceptions.DepartmentNotFoundException;
import ru.skypro.homework12.exceptions.employeeExceptions.*;


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

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeIncorrectFirstNameException.class)
    public ResponseEntity<String> employeeIncorrectFirstNameExceptionHandler(EmployeeIncorrectFirstNameException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeIncorrectLastNameException.class)
    public ResponseEntity<String> employeeIncorrectLastNameExceptionHandler(EmployeeIncorrectLastNameException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> departmentNotFoundExceptionHandler(DepartmentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
