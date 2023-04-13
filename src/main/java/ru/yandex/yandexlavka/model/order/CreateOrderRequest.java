package ru.yandex.yandexlavka.model.order;

import java.util.ArrayList;

public class CreateOrderRequest {
    ArrayList<CreateOrderDto> orders;
    CreateOrderRequest(ArrayList<CreateOrderDto> orders){ this.orders=orders;}
}

/*
"CreateOrderRequest": {
        "required": [
        "orders"
        ]
        }*/
