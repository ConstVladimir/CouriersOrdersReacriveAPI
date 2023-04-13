package ru.yandex.yandexlavka.model.order;

import ru.yandex.yandexlavka.model.courier.CouriersGroupOrders;

import java.time.OffsetDateTime;
import java.util.ArrayList;

public class OrderAssignResponse {
    OffsetDateTime date;
    ArrayList<CouriersGroupOrders> couriers;
}

/*
"OrderAssignResponse": {
        "required": [
        "date",
        "couriers"
        ]
        }*/
