package ru.yandex.yandexlavka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.yandexlavka.model.order.*;
import ru.yandex.yandexlavka.model.order.dto.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.model.order.dto.OrderDto;
import ru.yandex.yandexlavka.model.order.entity.OrderDB;
import ru.yandex.yandexlavka.repositories.OrdersRepositoryInterface2;
import ru.yandex.yandexlavka.services.interfaces.OrdersServiceInt;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Qualifier("orders-Service-1")
public class OrdersService implements OrdersServiceInt {
    @Autowired
    OrdersRepositoryInterface2 ordersRepository2;
    @Autowired
    MappingUtils mappingUtils;
    OrdersService(){}
    public Flux<OrderDto> createOrders (CreateOrderRequest createOrderRequest){
        List<OrderDB> orderDBList = createOrderRequest.getOrders().stream().map(mappingUtils::mappingToOrderDB).toList();
        return ordersRepository2.saveAll(orderDBList).map(mappingUtils::mappingToOrderDto);
    }
    public Flux<OrderDto> getOrders(int limit, int offset){
        return ordersRepository2.findAllOffsetLimit(offset, limit).map(mappingUtils::mappingToOrderDto);
    }
    public Mono<OrderDto> getOrderById(long id){
        return ordersRepository2.findById(id).map(mappingUtils::mappingToOrderDto);
    }
    public Flux<OrderDto> completeOrders  (CompleteOrderRequestDto completeOrderRequestDto) {
        return Flux.fromIterable(completeOrderRequestDto.getComplete_info())
                .map(completeOrder -> {
                   OrderDto orderDto = null;
                   Optional<OrderDB> dbOrderInfo = ordersRepository2.findById(completeOrder.getOrder_id()).blockOptional(Duration.ofMillis(100));
                   if (dbOrderInfo.isEmpty()){
                       // not found
                   }
                   else {
                       OrderDB existOrderDB = dbOrderInfo.get();
                       if (existOrderDB.getCourier_id() == null
                               || !Objects.equals(existOrderDB.getCourier_id(), completeOrder.getCourier_id()))
                       {
                           // not correct
                       }
                       else {
                           existOrderDB.setCompleted_time(completeOrder.getComplete_time());
                            orderDto = mappingUtils.mappingToOrderDto(existOrderDB);
                       }
                   }
                    return orderDto;
                })
                .filter(Objects::nonNull);
    }
    public Flux<OrderDB> getCourierOrdersBetweenDates (long courier_id, LocalDate startDate, LocalDate endDate){
        return ordersRepository2.getCourierOrdersBetweenDate(courier_id, startDate, endDate);
    }
}
