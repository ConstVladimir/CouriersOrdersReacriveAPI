package ru.yandex.yandexlavka.model.order;

import ru.yandex.yandexlavka.model.order.dto.OrderDto;

import java.util.ArrayList;

public class GroupOrders {
    long group_order_id;
    ArrayList<OrderDto> orders;
}

/*
"GroupOrders": {
        "required": [
        "group_order_id",
        "orders"
        ]
        }*/
