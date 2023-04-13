package ru.yandex.yandexlavka.model.courier;

import ru.yandex.yandexlavka.model.order.GroupOrders;

import java.util.ArrayList;

public class CouriersGroupOrders {
    long courier_id;
    ArrayList<GroupOrders> orders;
}

/*
"CouriersGroupOrders": {
        "required": [
        "courier_id",
        "orders"
        ],
        }*/
