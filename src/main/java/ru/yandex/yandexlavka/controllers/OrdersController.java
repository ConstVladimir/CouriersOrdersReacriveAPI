package ru.yandex.yandexlavka.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.courier.CourierDto;
import ru.yandex.yandexlavka.model.courier.CreateCourierRequest;
import ru.yandex.yandexlavka.model.courier.CreateCouriersResponse;
import ru.yandex.yandexlavka.model.courier.GetCouriersResponse;
import ru.yandex.yandexlavka.model.order.CreateOrderRequest;
import ru.yandex.yandexlavka.model.order.OrderDto;
import ru.yandex.yandexlavka.services.OrdersService;

import java.util.ArrayList;

@RestController
public class OrdersController {
    OrdersService ordersService;
    OrdersController(OrdersService ordersService){this.ordersService=ordersService;}

    @PostMapping("/orders")
    public ResponseEntity<?> createCourier(@RequestBody CreateOrderRequest createOrderRequest) {
        ArrayList<OrderDto> courierDtoArrayList = ordersService.createOrders(createOrderRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courierDtoArrayList);
    }
    @GetMapping("/orders/{order_id}")
    public ResponseEntity<?> getCouriers(@PathVariable long order_id){
        OrderDto needOrder = ordersService.GetOrderById(order_id);
        if (needOrder == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("{}");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(needOrder);
    }
    @GetMapping("/orders")
    public ResponseEntity<?> getCouriers(@RequestParam(required = false, defaultValue = "1") Integer limit, @RequestParam(required = false, defaultValue = "0") Integer offset){
        ArrayList<OrderDto> orderDtoArrayList = ordersService.getOrdersResponse(limit, offset);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderDtoArrayList);
    }
}
