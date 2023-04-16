package ru.yandex.yandexlavka.model.order;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateOrderRequest {
    ArrayList<CreateOrderDto> orders;
}

/*
"CreateOrderRequest": {
        "required": [
        "orders"
        ]
        }*/
