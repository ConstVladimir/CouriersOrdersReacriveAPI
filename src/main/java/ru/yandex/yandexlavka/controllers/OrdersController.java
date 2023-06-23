package ru.yandex.yandexlavka.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.yandex.yandexlavka.controllers.interfaces.OrderControllerInt;
import ru.yandex.yandexlavka.model.order.dto.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.model.order.CreateOrderRequest;
import ru.yandex.yandexlavka.model.order.OrderAssignResponse;
import ru.yandex.yandexlavka.model.order.dto.OrderDto;
import ru.yandex.yandexlavka.services.interfaces.AssignServiceInt;
import ru.yandex.yandexlavka.services.interfaces.OrdersServiceInt;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/orders")
@ResponseStatus(HttpStatus.OK)
public class OrdersController implements OrderControllerInt {
    @Qualifier("orders-Service-1")
    OrdersServiceInt ordersService;
    @Qualifier("assign-Service-1")
    AssignServiceInt assignService;
    @PostMapping("")
    public Flux<OrderDto> createOrders(@RequestBody CreateOrderRequest createOrderRequest) {
            return ordersService.createOrders(createOrderRequest);
    }
    @GetMapping("/{order_id}")
    public Mono<OrderDto> getOrders(@PathVariable long order_id){
        return ordersService.getOrderById(order_id);
    }
    @GetMapping("")
    public Flux<OrderDto> getOrders(@RequestParam(required = false, defaultValue = "1") int limit, @RequestParam(required = false, defaultValue = "0") int offset){
            return ordersService.getOrders(limit, offset);
    }
    @PostMapping("/complete")
    public Flux<OrderDto> completeOrders (@RequestBody CompleteOrderRequestDto completeOrderRequestDto){
            return ordersService.completeOrders(completeOrderRequestDto);
    }
    @PostMapping("/assign")
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<OrderAssignResponse> ordersAssign (@RequestParam(required = false) LocalDate date){
            if (date == null) date = LocalDate.now();
            return assignService.getOrderAssignResponse(date);
    }
}