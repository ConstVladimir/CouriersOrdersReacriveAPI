package ru.yandex.yandexlavka.controllers;

import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.order.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.model.order.CreateOrderRequest;
import ru.yandex.yandexlavka.model.order.OrderAssignResponse;
import ru.yandex.yandexlavka.model.order.OrderDto;
import ru.yandex.yandexlavka.ratelimiter.RateLimiter;
import ru.yandex.yandexlavka.services.OrdersService;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {
    @Autowired
    OrdersService ordersService;
    @PostMapping("")
    public ResponseEntity<ArrayList<OrderDto>> createOrders(@RequestBody CreateOrderRequest createOrderRequest) {
            ArrayList<OrderDto> orderDtoArrayList = ordersService.createOrders(createOrderRequest);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(orderDtoArrayList);
    }
    @GetMapping("/{order_id}")
    public ResponseEntity<?> getOrders(@PathVariable long order_id){
            OrderDto needOrder = ordersService.GetOrderById(order_id);
            if (needOrder == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("{}");
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(needOrder);
    }
    @GetMapping("")
    public ResponseEntity<ArrayList<OrderDto>> getOrders(@RequestParam(required = false, defaultValue = "1") Integer limit, @RequestParam(required = false, defaultValue = "0") Integer offset){
            ArrayList<OrderDto> orderDtoArrayList = ordersService.getOrdersResponse(limit, offset);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(orderDtoArrayList);
    }
    @PostMapping("/complete")
    public ResponseEntity<?> completeOrders (@RequestBody CompleteOrderRequestDto completeOrderRequestDto){
            ArrayList<OrderDto> orderDtoArrayList = ordersService.completeOrders(completeOrderRequestDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(orderDtoArrayList);

    }
    @PostMapping("/assign")
    public ResponseEntity<?> ordersAssign (@RequestParam(required = false) LocalDate date){
            if (date == null) date = LocalDate.now();
            ArrayList<OrderAssignResponse> orderAssignResponseArrayList = new ArrayList<>();

            return ResponseEntity.accepted().body(orderAssignResponseArrayList);
    }
}
