package ru.yandex.yandexlavka.controllers.interfaces;

import jakarta.validation.ConstraintViolationException;
import reactor.core.publisher.Mono;
import ru.yandex.yandexlavka.model.BadRequestResponse;
import ru.yandex.yandexlavka.model.NotFoundResponse;

import java.util.NoSuchElementException;

public interface ExceptionControllerInt {
    //public Mono<NotFoundResponse> handleEntityNotFoundException  (EntityNotFoundException exception);
    public Mono<NotFoundResponse> handleNoSuchElementException  (NoSuchElementException exception);
    public Mono<BadRequestResponse> handleConstraintViolationException  (ConstraintViolationException exception);
}
