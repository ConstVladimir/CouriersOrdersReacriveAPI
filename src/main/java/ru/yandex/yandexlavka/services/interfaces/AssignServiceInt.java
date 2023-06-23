package ru.yandex.yandexlavka.services.interfaces;

import reactor.core.publisher.Flux;
import ru.yandex.yandexlavka.model.order.OrderAssignResponse;

import java.time.LocalDate;

public interface AssignServiceInt {
    public Flux<OrderAssignResponse> getOrderAssignResponse (LocalDate date);
}
