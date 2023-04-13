package ru.yandex.yandexlavka.model.order;

import java.time.OffsetDateTime;
import java.util.ArrayList;

public class OrderDto {
    long order_id;
    float weight;
    int regions;
    ArrayList<String> delivery_hours;
    int cost;
    OffsetDateTime completed_time;
}

/*
"OrderDto": {
        "required": [
        "cost",
        "delivery_hours",
        "order_id",
        "regions",
        "weight"
        ]
        }*/
