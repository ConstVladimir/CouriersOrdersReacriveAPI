package ru.yandex.yandexlavka.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.yandexlavka.model.BadRequestResponse;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException  (EntityNotFoundException exception){
        return ResponseEntity
                .badRequest()
                .body("{}");
    }
}
