package ru.yandex.yandexlavka.controllers.interfaces;

import java.time.LocalDate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.yandexlavka.model.order.CreateOrderRequest;
import ru.yandex.yandexlavka.model.order.OrderAssignResponse;
import ru.yandex.yandexlavka.model.order.dto.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.model.order.dto.OrderDto;

public interface OrderControllerInt {
    public Flux<OrderDto> createOrders(CreateOrderRequest createOrderRequest);
    public Mono<OrderDto> getOrders(long order_id);
    public Flux<OrderDto> getOrders(int limit, int offset);
    public Flux<OrderDto> completeOrders (CompleteOrderRequestDto completeOrderRequestDto);
    public Flux<OrderAssignResponse> ordersAssign (LocalDate date);
}
