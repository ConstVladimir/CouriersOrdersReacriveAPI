package ru.yandex.yandexlavka.model.order;

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
