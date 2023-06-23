package ru.yandex.yandexlavka.controllers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import ru.yandex.yandexlavka.controllers.interfaces.ExceptionControllerInt;
import ru.yandex.yandexlavka.model.BadRequestResponse;
import ru.yandex.yandexlavka.model.NotFoundResponse;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionController implements ExceptionControllerInt {
    /*@ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<NotFoundResponse> handleEntityNotFoundException  (EntityNotFoundException exception){
        return Mono.just(new NotFoundResponse());
    }*/
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<NotFoundResponse> handleNoSuchElementException  (NoSuchElementException exception){
        return Mono.just(new NotFoundResponse());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<BadRequestResponse> handleConstraintViolationException  (ConstraintViolationException exception){
        return Mono.just(new BadRequestResponse());
    }
}
