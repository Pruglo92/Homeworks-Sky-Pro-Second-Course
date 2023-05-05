package ru.skypro.homework10.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Недопустимый тип ID. ID может содержать только цифры.");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID не может быть пустым.");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IdNotNullException.class)
    public ResponseEntity<String> handleIdNotNullException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID не может быть 0.");
    }
}
