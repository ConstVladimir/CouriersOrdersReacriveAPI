package ru.yandex.yandexlavka.services.interfaces;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.yandexlavka.model.order.CreateOrderRequest;
import ru.yandex.yandexlavka.model.order.dto.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.model.order.dto.OrderDto;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;

import java.time.LocalDate;

public interface OrdersServiceInt {
    public Flux<OrderDto> createOrders (CreateOrderRequest createOrderRequest);
    public Flux<OrderDto> getOrders(int limit, int offset);
    public Mono<OrderDto> getOrderById(long id);
    public Flux<OrderDto> completeOrders  (CompleteOrderRequestDto completeOrderRequestDto) ;
    public Flux<OrderDB> getCourierOrdersBetweenDates (long courier_id, LocalDate startDate, LocalDate endDate);
}
